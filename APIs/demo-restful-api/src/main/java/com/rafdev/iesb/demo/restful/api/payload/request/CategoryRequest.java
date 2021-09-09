package com.rafdev.iesb.demo.restful.api.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryRequest {

    @Size(min = 3)
    @NotBlank
    private String name;
}
