package com.restdocspractice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        PostResponse post = postService.create(postRequest);
        return ResponseEntity.created(null).body(post);
    }

    @GetMapping
    public ResponseEntity<?> getPost(@RequestParam("postId") Long postId) {
        PostResponse post = postService.getPost(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
