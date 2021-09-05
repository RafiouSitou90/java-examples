package com.rafdev.iesb.demo.restful.api.entity.category;

import com.rafdev.iesb.demo.restful.api.entity.BasePage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryPage extends BasePage {
    protected String sortBy = "name";
}
