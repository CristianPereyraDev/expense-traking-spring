package com.cristiandev.expensetraking.repository.impl;

import com.cristiandev.expensetraking.repository.CategoryRepository;
import com.cristiandev.expensetraking.entities.Category;
import com.cristiandev.expensetraking.repository.mappers.CategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class CategoryRepositoryImplH2 implements CategoryRepository {
    private static final String INSERT_INTO_CATEGORY = "INSERT INTO category (name) values (?)";
    private static final String SELECT_FROM_CATEGORY_ALL = "SELECT * FROM category";
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CategoryRepositoryImplH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Integer insert(Category categoryDto) {
        return jdbcTemplate.update(INSERT_INTO_CATEGORY, categoryDto.getName().toLowerCase());
    }

    @Override
    public Category read(Integer id) {
        return null;
    }

    @Override
    public Integer update(Category categoryDto) {
        return 1;
    }

    @Override
    public Integer delete(Integer id) {
        return 1;
    }

    @Override
    public List<Category> getAll() {
        return jdbcTemplate.query(SELECT_FROM_CATEGORY_ALL, new CategoryRowMapper());
    }
}
