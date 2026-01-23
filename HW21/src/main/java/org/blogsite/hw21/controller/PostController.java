package org.blogsite.hw21.controller;

import org.blogsite.hw21.model.Post;
import org.blogsite.hw21.service.PostService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PostController {

    private final PostService postService;
    private static final String UPLOAD_DIR = "uploads/images/posts/";
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/new")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "create_post";
    }

    @PostMapping("/posts/new")
    public String createPost(@ModelAttribute("post") Post post,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             RedirectAttributes redirectAttributes) {
        if (!imageFile.isEmpty()) {
            if (imageFile.getSize() > MAX_FILE_SIZE) {
                redirectAttributes.addFlashAttribute("errorMessage", "Image file size exceeds the limit of 5MB.");
                return "redirect:/posts/new";
            }
            try {
                String projectRoot = System.getProperty("user.dir");
                Path uploadPath = Paths.get(projectRoot, UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String originalFilename = imageFile.getOriginalFilename();
                String fileExtension = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                }
                String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
                Path filePath = uploadPath.resolve(uniqueFileName);

                Files.copy(imageFile.getInputStream(), filePath);
                post.setImageUrl("/uploads/images/posts/" + uniqueFileName);

            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("errorMessage", "Failed to upload image: " + e.getMessage());
                return "redirect:/posts/new";
            }
        }

        postService.createNewPost(post);
        redirectAttributes.addFlashAttribute("successMessage", "Post created successfully!");
        return "redirect:/";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditPostForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Post> postOptional = postService.findPostById(id);
        if (postOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Post not found.");
            return "redirect:/";
        }

        Post post = postOptional.get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        if (!post.getAuthor().getUsername().equals(currentUsername) && !isAdmin) {
            redirectAttributes.addFlashAttribute("errorMessage", "You are not authorized to edit this post.");
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "edit_post";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable Long id,
                             @ModelAttribute("post") Post post,
                             @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                             RedirectAttributes redirectAttributes) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                if (imageFile.getSize() > MAX_FILE_SIZE) {
                    redirectAttributes.addFlashAttribute("errorMessage", "Image file size exceeds the limit of 5MB.");
                    return "redirect:/posts/" + id + "/edit";
                }

                String projectRoot = System.getProperty("user.dir");
                Path uploadPath = Paths.get(projectRoot, UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String originalFilename = imageFile.getOriginalFilename();
                String fileExtension = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                }
                String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
                Path filePath = uploadPath.resolve(uniqueFileName);

                Files.copy(imageFile.getInputStream(), filePath);
                post.setImageUrl("/uploads/images/posts/" + uniqueFileName);
            } else {
                Optional<Post> existingPostOptional = postService.findPostById(id);
                existingPostOptional.ifPresent(existingPost -> post.setImageUrl(existingPost.getImageUrl()));
            }

            postService.updatePost(id, post);
            redirectAttributes.addFlashAttribute("successMessage", "Post updated successfully!");
            return "redirect:/";
        } catch (AccessDeniedException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/";
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to upload image: " + e.getMessage());
            return "redirect:/posts/" + id + "/edit";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            postService.deletePost(id);
            redirectAttributes.addFlashAttribute("successMessage", "Post deleted successfully!");
            return "redirect:/";
        } catch (AccessDeniedException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting post: " + e.getMessage());
            return "redirect:/";
        }
    }
}
