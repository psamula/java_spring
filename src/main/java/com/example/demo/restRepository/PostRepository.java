package com.example.demo.restRepository;

import com.example.demo.restModel.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitle(String title);
    @Query("select p from Post p")
    List<Post> findAllPosts(Pageable page);
    List<Post> findAllByAuthor(Pageable page, String author);
}
