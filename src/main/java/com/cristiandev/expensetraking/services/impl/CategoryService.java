package com.cristiandev.expensetraking.services.impl;

import com.cristiandev.expensetraking.dto.CategoryDto;
import com.cristiandev.expensetraking.repository.impl.CategoryRepositoryImplH2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepositoryImplH2 categoryRepository;
    @Autowired
    public CategoryService(CategoryRepositoryImplH2 categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Integer createCategory(CategoryDto categoryDto) {
        return categoryRepository.insert(categoryDto);
    }
}
