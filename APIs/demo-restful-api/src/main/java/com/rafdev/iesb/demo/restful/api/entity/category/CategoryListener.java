package com.rafdev.iesb.demo.restful.api.entity.category;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class CategoryListener {
    @PrePersist
    @PreUpdate
    public void onPrePersist(final Category category) {
        category.computeSlug();
    }
}
