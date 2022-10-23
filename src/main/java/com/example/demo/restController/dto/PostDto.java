package com.example.demo.restController.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostDto {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
}
