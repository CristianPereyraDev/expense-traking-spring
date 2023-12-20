package com.cristiandev.expensetraking.services;

import com.cristiandev.expensetraking.repository.CategoryRepository;
import com.cristiandev.expensetraking.dto.CategoryDto;
import com.cristiandev.expensetraking.repository.impl.CategoryRepositoryImplH2;

public class CategoryService {
    private final CategoryRepository categoryDao;

    public CategoryService() {
        categoryDao = new CategoryRepositoryImplH2();
    }

    public Integer createCategory(CategoryDto categoryDto) {
        return categoryDao.insert(categoryDto);
    }
}
