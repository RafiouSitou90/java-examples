package com.rafdev.iesb.demo.restful.api.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class BasePage {
    protected int pageNumber = 0;
    protected int pageSize = 10;
    protected Sort.Direction sortDirection = Sort.Direction.ASC;
    protected String sortBy;
}
