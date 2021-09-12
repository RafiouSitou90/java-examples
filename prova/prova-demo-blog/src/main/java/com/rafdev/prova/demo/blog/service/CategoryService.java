package com.rafdev.prova.demo.blog.service;

import com.rafdev.prova.demo.blog.dto.CategoryDto;
import com.rafdev.prova.demo.blog.payload.request.CategoryCreationRequest;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryCreationRequest categoryRequest);

    CategoryDto updateCategoryById(Long id, CategoryCreationRequest categoryRequest);

    List<CategoryDto> getCategories();

    CategoryDto getCategoryById(Long id);

    void deleteCategoryById(Long id);
}
