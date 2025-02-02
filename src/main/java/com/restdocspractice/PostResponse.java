package com.restdocspractice;

import java.util.List;

public record PostResponse(String title, String writer, String contents) {

    public static PostResponse of(Post post) {
        return new PostResponse(post.getTitle(), post.getWriter(), post.getContents());
    }

    public static List<PostResponse> listOf(List<Post> savedPostList) {
        return savedPostList.stream()
                .map(PostResponse::of)
                .toList();
    }
}
