package com.example.demo.restService;

import com.example.demo.restModel.Post;
import com.example.demo.restRepository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
