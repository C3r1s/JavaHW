package org.blogsite.hwthymeleaf.controller;

import org.blogsite.hwthymeleaf.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MyController {

    private List<Product> products = new ArrayList<>();

    public MyController() {
        products.add(new Product(1L, "Laptop", "Electronics", 1200.00));
        products.add(new Product(2L, "Mouse", "Electronics", 25.00));
        products.add(new Product(3L, "Keyboard", "Electronics", 75.00));
        products.add(new Product(4L, "Desk Chair", "Furniture", 150.00));
        products.add(new Product(5L, "Monitor", "Electronics", 300.00));
        products.add(new Product(6L, "Coffee Table", "Furniture", 80.00));
    }

    @GetMapping("/")
    public String home(
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            Model model) {

        List<Product> filteredProducts = products.stream()
                .filter(p -> category == null || category.isEmpty() || p.getCategory().equalsIgnoreCase(category))
                .filter(p -> minPrice == null || p.getPrice() >= minPrice)
                .collect(Collectors.toList());

        model.addAttribute("products", filteredProducts);
        model.addAttribute("message", "Welcome to our Product Catalog!");
        model.addAttribute("userRole", "ADMIN"); // Simulate user role for conditional display
        return "home";
    }

    @GetMapping("/product/{id}")
    public String productDetails(@PathVariable("id") Long id, Model model) {
        Product product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (product == null) {
            return "redirect:/"; // Redirect to home if product not found
        }

        model.addAttribute("product", product);
        model.addAttribute("isAdmin", true); // Simulate admin access for this page
        return "product-details";
    }

    // Existing methods (conditionalPage, anotherPage) can remain or be removed if not needed for this example
    @GetMapping("/conditional")
    public String conditionalPage(@RequestParam(name = "show", required = false, defaultValue = "false") boolean show, Model model) {
        model.addAttribute("showContent", show);
        model.addAttribute("conditionalMessage", "This content is shown based on a condition!");
        return "conditional";
    }

    @GetMapping("/another-page")
    public String anotherPage(Model model) {
        model.addAttribute("title", "Another Page");
        model.addAttribute("content", "This is another page accessible from the home page.");
        return "another-page";
    }
}
