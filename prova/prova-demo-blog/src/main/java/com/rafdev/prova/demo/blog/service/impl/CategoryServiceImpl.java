package com.rafdev.prova.demo.blog.service.impl;

import com.rafdev.prova.demo.blog.dto.CategoryDto;
import com.rafdev.prova.demo.blog.entity.Category;
import com.rafdev.prova.demo.blog.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.demo.blog.exception.ResourceNotFoundException;
import com.rafdev.prova.demo.blog.payload.request.CategoryCreationRequest;
import com.rafdev.prova.demo.blog.repository.CategoryRepository;
import com.rafdev.prova.demo.blog.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl() {
        this.categoryRepository = new CategoryRepository();
    }

    @Override
    public CategoryDto saveCategory(CategoryCreationRequest categoryRequest) {
        if (categoryRepository.existsById(categoryRequest.getId())) {
            throw new ResourceAlreadyExistsException("Category", "Id", categoryRequest.getId());
        }

        if (categoryRepository.existsByName(categoryRequest.getName())) {
            throw new ResourceAlreadyExistsException("Category", "Name", categoryRequest.getName());
        }

        Category category = new Category(categoryRequest.getId(), categoryRequest.getName());
        Category categoryCreated = categoryRepository.save(category);

        return setCategoryDto(categoryCreated);
    }

    @Override
    public CategoryDto updateCategoryById(Long id, CategoryCreationRequest categoryRequest) {
        Category categoryFound = categoryRepository.findById(id);

        if (categoryFound == null) {
            throw new ResourceNotFoundException("Category", "Id", id);
        }

        Category category = new Category(categoryRequest.getId(), categoryRequest.getName());

        categoryFound.setName(category.getName());

        Category categoryUpdated = categoryRepository.update(categoryFound);

        return setCategoryDto(categoryUpdated);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<CategoryDto> categoriesDto = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();

        for (Category category: categories) {
            CategoryDto categoryDto = new CategoryDto(category.getId(), category.getName());

            categoriesDto.add(categoryDto);
        }

        return categoriesDto;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id);

        if (category == null) {
            throw new ResourceNotFoundException("Category", "Id", id);
        }

        return setCategoryDto(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id);

        if (category == null) {
            throw new ResourceNotFoundException("Category", "Id", id);
        }

        categoryRepository.delete(category.getId());
    }

    private CategoryDto setCategoryDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
