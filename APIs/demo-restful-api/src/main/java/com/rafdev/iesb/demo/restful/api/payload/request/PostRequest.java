package com.rafdev.iesb.demo.restful.api.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    @NotBlank
    @Size(min = 3)
    private String title;

    @NotBlank
    @Size(min = 3)
    private String summary;

    @NotBlank
    @Size(min = 10)
    private String content;

    private String imageUrl;

    private LocalDateTime publishedAt;

    private Long category;

    private Long user;

    private Set<String> tags;
}
