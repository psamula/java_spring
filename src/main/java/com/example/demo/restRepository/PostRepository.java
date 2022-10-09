package com.example.demo.restRepository;

import com.example.demo.restModel.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post where title = ?1")
    List<Post>  findAllByTitle(String title);
}
