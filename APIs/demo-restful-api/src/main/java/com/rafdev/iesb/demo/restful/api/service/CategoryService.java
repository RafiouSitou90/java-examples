package com.rafdev.iesb.demo.restful.api.service;

import com.rafdev.iesb.demo.restful.api.entity.category.Category;
import com.rafdev.iesb.demo.restful.api.entity.category.CategoryPage;
import com.rafdev.iesb.demo.restful.api.payload.request.CategoryRequest;
import org.springframework.data.domain.Page;

public interface CategoryService {
    Category saveCategory(CategoryRequest categoryRequest);

    Page<Category> getCategories(CategoryPage categoryPage);

    Category getCategoryById(Long id);

    Category updateCategoryById(Long id, Category category);

    void deleteCategoryById(Long id);
}
