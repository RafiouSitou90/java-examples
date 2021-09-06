package com.rafdev.iesb.demo.restful.api.entity.user;

import com.rafdev.iesb.demo.restful.api.entity.BasePage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPage extends BasePage {
    protected String sortBy = "username";
}
