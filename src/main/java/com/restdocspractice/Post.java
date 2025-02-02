package com.restdocspractice;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    private Long id;
    private String title;
    private String writer;
    private String contents;

    @Builder
    private Post(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }
}
