package com.rafdev.prova.demo.blog.repository;

import com.rafdev.prova.demo.blog.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryRepository {

    private final List<Category> categories;

    public CategoryRepository() {
        this.categories = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            this.categories.add(new Category((long) i, "New category name #" + i));
        }
    }

    public List<Category> findAll() {
        return categories;
    }

    public Category save(Category category) {
        categories.add(category);

        return category;
    }

    public Category update(Category category) {
        Category categoryBD = findById(category.getId());

        if (categoryBD != null) {
            categories.set(categories.indexOf(categoryBD), category);
        }

        return category;
    }

    public void delete(Long id) {
        categories.removeIf(category -> Objects.equals(category.getId(), id));
    }

    public Category findById(Long id) {
        for (Category category: categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }

        return null;
    }

    public Category findByName(String name) {
        for (Category category: categories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }

        return null;
    }

    public boolean existsByName(String name) {
        for (Category category: categories) {
            if (category.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public boolean existsById(Long id) {
        for (Category category: categories) {
            if (category.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }
}
