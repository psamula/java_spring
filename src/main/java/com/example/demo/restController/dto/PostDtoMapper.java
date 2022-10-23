package com.example.demo.restController.dto;

import com.example.demo.restController.dto.PostDto;
import com.example.demo.restModel.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostDtoMapper {

    private PostDtoMapper() {}

    public static List<PostDto> mapToPostDtos (List<Post> posts) {
        return posts.stream()
                .map(post -> mapToPostDto(post))
                .collect(Collectors.toList());
    }

    private static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .content(post.getContent())
                .title(post.getTitle())
                .created(post.getCreated())
                .build();
    }
}
