package com.restdocspractice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public PostResponse create(PostRequest postRequest) {
        Post savedPost = postRepository.save(PostRequest.of(postRequest));
        return PostResponse.of(savedPost);
    }

    public PostResponse getPost(Long postId) {
        Post foundPost = postRepository.findById(postId);
        return PostResponse.of(foundPost);
    }

    public List<PostResponse> getAllPosts() {
        return PostResponse.listOf(postRepository.findAll());
    }
}
