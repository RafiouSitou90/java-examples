package com.rafdev.iesb.demo.restful.api.entity.comment;

import com.rafdev.iesb.demo.restful.api.entity.BasePage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class CommentPage extends BasePage {
    protected String sortBy = "publishedAt";
    protected Sort.Direction sortDirection = Sort.Direction.DESC;
}
