package com.cristiandev.expensetraking.controller;

import com.cristiandev.expensetraking.entities.Category;
import com.cristiandev.expensetraking.exceptions.DaoException;
import com.cristiandev.expensetraking.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<Category>> allCategoriesHandler() throws DaoException {
        List<Category> response = categoryService.getAllCategories();

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
