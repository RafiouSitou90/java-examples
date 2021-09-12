package com.rafdev.prova.demo.blog.controller;

import com.rafdev.prova.demo.blog.dto.CategoryDto;
import com.rafdev.prova.demo.blog.exception.ResourceNotFoundException;
import com.rafdev.prova.demo.blog.payload.request.CategoryCreationRequest;
import com.rafdev.prova.demo.blog.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryCreationRequest categoryRequest) {
        return new ResponseEntity<>(categoryService.saveCategory(categoryRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable("id") Long id,
                                                          @RequestBody CategoryCreationRequest categoryRequest)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(categoryService.updateCategoryById(id, categoryRequest), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        categoryService.deleteCategoryById(id);

        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}
