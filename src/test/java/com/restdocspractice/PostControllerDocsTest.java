package com.restdocspractice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class PostControllerDocsTest extends RestDocsSupport {
    // Mock 설정
    private final PostService postService = Mockito.mock(PostService.class);

    @Override
    protected Object initController() {
        return new PostController(postService);
    }

    @DisplayName("게시글을 등록한다.")
    @Test
    void createPost() throws Exception {
        // given
        String title = "모각코 인원 모집";
        String writer = "김준성";
        String contents = "주말에 모각코 하실분?";
        PostRequest request = new PostRequest(title, writer, contents);

        BDDMockito.given(
                        postService.create(any(PostRequest.class)))
                .willReturn(new PostResponse(title, writer, contents));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/posts")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.writer").value(writer))
                .andExpect(jsonPath("$.contents").value(contents))


                // RestDoc 작성을 위한 메서드 체이닝
                .andDo(MockMvcRestDocumentation.document("post-create", // 식별자
                        preprocessRequest(prettyPrint()), // 한줄로 표현된 json을 보기좋게 변경
                        preprocessResponse(prettyPrint()), // 한줄로 표현된 json을 보기좋게 변경
                        PayloadDocumentation.requestFields(
                                // Controller 요청 DTO 필드 정의
                                PayloadDocumentation.fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("글 제목"),
                                PayloadDocumentation.fieldWithPath("writer").type(JsonFieldType.STRING)
                                        .description("작성자"),
                                PayloadDocumentation.fieldWithPath("contents").type(JsonFieldType.STRING)
//                                        .optional() // templates/request-fields.snippet 을 통해 Optional 체크 설정
                                        .description("글 내용")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("글 제목"),
                                PayloadDocumentation.fieldWithPath("writer").type(JsonFieldType.STRING)
                                        .description("작성자"),
                                PayloadDocumentation.fieldWithPath("contents").type(JsonFieldType.STRING)
                                        .description("글 내용")
                        )
                ));
    }
}
