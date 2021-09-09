package com.rafdev.iesb.demo.restful.api.service.impl;

import com.rafdev.iesb.demo.restful.api.entity.category.Category;
import com.rafdev.iesb.demo.restful.api.entity.category.CategoryPage;
import com.rafdev.iesb.demo.restful.api.exception.ResourceNotFoundException;
import com.rafdev.iesb.demo.restful.api.payload.request.CategoryRequest;
import com.rafdev.iesb.demo.restful.api.repository.CategoryRepository;
import com.rafdev.iesb.demo.restful.api.service.CategoryService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final String resourceName = "Category";
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(CategoryRequest categoryRequest) {
        Category category = new Category(categoryRequest.getName());

        return categoryRepository.save(category);
    }

    @Override
    public Page<Category> getCategories(CategoryPage categoryPage) {
        Sort sort = Sort.by(categoryPage.getSortDirection(), categoryPage.getSortBy());
        Pageable pageable = PageRequest.of(categoryPage.getPageNumber(), categoryPage.getPageSize(), sort);

        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, "id", id));
    }

    @Override
    public Category updateCategoryById(Long id, Category category) {
        Category oldCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, "id", id));

        oldCategory.setName(category.getName());

        return categoryRepository.save(oldCategory);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, "id", id));

        categoryRepository.deleteById(category.getId());
    }
}
