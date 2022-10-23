package com.example.demo.restService;

import com.example.demo.restModel.Comment;
import com.example.demo.restModel.Post;
import com.example.demo.restRepository.CommentRepository;
import com.example.demo.restRepository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    public List<Post> getPosts(int page, Sort.Direction sort) {
        return postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE,
                Sort.by(sort, "id")));
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE,
                Sort.by(sort, "id"))); //gathers all posts from database
        List<Long> ids = allPosts.stream() // gathers all post ids
                .map(Post::getId)
                .collect(Collectors.toList());
        var comments = commentRepository.findAllByPostIdIn(ids); //gathers all comments by post_id table field
        allPosts.forEach(post -> post.setCommentList(extractComments(comments, post.getId()))); //assigns comments to
                                                                                                //the post it belongs to

        return allPosts;
    }
    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList()); //list of all comments of base and certain post_id, if the comment belongs to the
                                                //post with passed id then it sets its comments list to the returned list
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post editPost(Post post) {
        var editedPost = postRepository.findById(post.getId()).orElseThrow();
        editedPost.setContent(post.getContent());
        editedPost.setTitle(post.getTitle());
        return editedPost;
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
