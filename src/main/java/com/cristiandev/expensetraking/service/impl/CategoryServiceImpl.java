package com.cristiandev.expensetraking.service.impl;

import com.cristiandev.expensetraking.dto.request.CategoryRequestDto;
import com.cristiandev.expensetraking.entities.Category;
import com.cristiandev.expensetraking.exceptions.DaoException;
import com.cristiandev.expensetraking.repository.CategoryRepository;
import com.cristiandev.expensetraking.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Integer createCategory(CategoryRequestDto categoryRequestDto) throws DaoException {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return categoryRepository.insert(category);
    }

    @Override
    public List<Category> getAllCategories() throws DaoException {
        return categoryRepository.getAll();
    }
}
