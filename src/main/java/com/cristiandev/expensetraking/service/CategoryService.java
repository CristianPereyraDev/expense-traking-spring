package com.cristiandev.expensetraking.service;

import com.cristiandev.expensetraking.dto.request.CategoryRequestDto;
import com.cristiandev.expensetraking.entities.Category;
import com.cristiandev.expensetraking.exceptions.DaoException;

import java.util.List;

public interface CategoryService {
    Integer createCategory(CategoryRequestDto category) throws DaoException;

    List<Category> getAllCategories() throws DaoException;
}
