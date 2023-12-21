package com.cristiandev.expensetraking.repository.impl;

import com.cristiandev.expensetraking.entities.Category;
import com.cristiandev.expensetraking.exceptions.DaoException;
import com.cristiandev.expensetraking.repository.ExpenseRepository;
import com.cristiandev.expensetraking.entities.Expense;
import com.cristiandev.expensetraking.repository.mappers.CategoryRowMapper;
import com.cristiandev.expensetraking.repository.mappers.ExpenseRowMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpenseRepositoryImplH2 implements ExpenseRepository {
    private static final String INSERT_INTO_EXPENSE = "INSERT INTO expense (date, amount, description, category_id, category_name) values (?, ?, ?, ?, ?)";
    private static final String UPDATE_EXPENSE = "UPDATE expense SET date=?, amount=?, description=?, category_id=? WHERE id=?";
    private static final String DELETE_FROM_EXPENSE_BY_ID = "DELETE FROM expense WHERE id=?";
    private static final String SELECT_FROM_EXPENSE_BY_ID = "SELECT * FROM expense WHERE id=?";
    private static final String SELECT_FROM_EXPENSE_ALL = "SELECT * FROM expense";
    private static final String INSERT_INTO_CATEGORY = "INSERT INTO category (name) values (?)";
    private static final String SELECT_FROM_CATEGORY_BY_NAME = "SELECT * FROM category WHERE name=?";
    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepositoryImplH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insert(Expense expense) throws DaoException {
        Category category;
        Object[] params = {expense.getCategoryName().toLowerCase()};
        int[] types = {1};

        try {
            jdbcTemplate.update(INSERT_INTO_CATEGORY, expense.getCategoryName().toLowerCase());
            category = jdbcTemplate.queryForObject(SELECT_FROM_CATEGORY_BY_NAME, params, types, new CategoryRowMapper());
        } catch (DuplicateKeyException e) {
            category = jdbcTemplate.queryForObject(SELECT_FROM_CATEGORY_BY_NAME, params, types, new CategoryRowMapper());
        }

        return jdbcTemplate.update(INSERT_INTO_EXPENSE,
                expense.getDate(),
                expense.getAmount(),
                expense.getDescription(),
                category.getId(),
                category.getName()
        );
    }

    @Override
    public Expense read(Long id) {
        Object[] params = {id};
        int[] types = {1};

        return jdbcTemplate.queryForObject(SELECT_FROM_EXPENSE_BY_ID, params, types, new ExpenseRowMapper());
    }

    @Override
    public Integer update(Expense expense) {
        return jdbcTemplate.update(UPDATE_EXPENSE,
                expense.getDate(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getCategoryId(),
                expense.getCategoryName()
        );
    }

    @Override
    public Integer delete(Long id) {
        return jdbcTemplate.update(DELETE_FROM_EXPENSE_BY_ID, id);
    }

    @Override
    public List<Expense> getAll() {
        return jdbcTemplate.query(SELECT_FROM_EXPENSE_ALL, new ExpenseRowMapper());
    }

    @Override
    public List<Expense> getExpensesByCategory(String categoryName) {
        String sql = "SELECT * FROM expense WHERE category_name=?";
        Object[] params = {categoryName};
        int[] types = {1};
        return jdbcTemplate.query(sql, params, types, new ExpenseRowMapper());
    }

}
