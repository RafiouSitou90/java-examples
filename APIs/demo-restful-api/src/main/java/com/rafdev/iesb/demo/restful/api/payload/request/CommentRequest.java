package com.rafdev.iesb.demo.restful.api.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CommentRequest {
    @NotBlank
    @Size(min = 5)
    private String content;

    private Long userId;

    private Long postId;
}
