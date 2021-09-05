package com.rafdev.iesb.demo.restful.api.entity.post;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class PostListener {
    @PrePersist
    @PreUpdate
    public void onPrePersist(final Post post) {
        post.computeSlug();
    }
}
