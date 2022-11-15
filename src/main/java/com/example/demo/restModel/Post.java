package com.example.demo.restModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
    @ApiModelProperty(hidden = true)
    private String author;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "postId", updatable = false, insertable = false)
    private List<Comment> commentList;

//    @ManyToOne
//    @JoinColumn(name = "")
//    private String author;
}
