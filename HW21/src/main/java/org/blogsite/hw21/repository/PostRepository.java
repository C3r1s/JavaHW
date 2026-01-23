package org.blogsite.hw21.repository;

import org.blogsite.hw21.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
