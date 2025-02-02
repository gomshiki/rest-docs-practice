package com.restdocspractice;


public record PostRequest(String title, String writer, String contents) {

    public static Post of(PostRequest postRequest) {
        return Post.builder()
                .title(postRequest.title)
                .contents(postRequest.contents)
                .writer(postRequest.writer)
                .build();
    }
}
