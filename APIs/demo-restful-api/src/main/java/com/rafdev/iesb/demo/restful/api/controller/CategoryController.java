package com.rafdev.iesb.demo.restful.api.controller;

import com.rafdev.iesb.demo.restful.api.entity.category.Category;
import com.rafdev.iesb.demo.restful.api.entity.category.CategoryPage;
import com.rafdev.iesb.demo.restful.api.service.CategoryService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Page<Category>> getCategories(CategoryPage categoryPage) {
        return new ResponseEntity<>(categoryService.getCategories(categoryPage), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable("id") Long id, @Valid @RequestBody Category category ) {
        return new ResponseEntity<>(categoryService.updateCategoryById(id, category), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> updatePatchCategoryById(@PathVariable("id") Long id,
                                                        @Valid @RequestBody Category category ) {
        return new ResponseEntity<>(categoryService.updateCategoryById(id, category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);

        return new ResponseEntity<>("Category deleted successfully!", HttpStatus.OK);
    }
}
