package com.rafdev.iesb.demo.restful.api.entity.post;

import com.rafdev.iesb.demo.restful.api.entity.BasePage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPage extends BasePage {
    protected String sortBy = "title";
}
