package com.cristiandev.expensetraking.repository.impl;

import com.cristiandev.expensetraking.entities.Category;
import com.cristiandev.expensetraking.entities.Expense;
import com.cristiandev.expensetraking.exceptions.DaoException;
import com.cristiandev.expensetraking.repository.ExpenseRepository;
import com.cristiandev.expensetraking.repository.mappers.CategoryRowMapper;
import com.cristiandev.expensetraking.repository.mappers.ExpenseRowMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExpenseRepositoryImplH2Test {

    @Mock
    private JdbcTemplate jdbcTemplateMock;
    private ExpenseRepository expenseRepository;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        // Inject jdbcTemplate
        expenseRepository = new ExpenseRepositoryImplH2(jdbcTemplateMock);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Given a valid Expense, insert should be add a new register.")
    void insert_ShouldInsertExpense_WhenValidExpenseDto() throws DaoException {
        // GIVEN
        Expense expense = new Expense();
        expense.setDate("2023-08-12");
        expense.setAmount(100.0);
        expense.setDescription("Expense description");
        expense.setCategoryName("Food");
        Category category = new Category();
        category.setName("Food");
        category.setId(1L);

        Object[] params = {expense.getCategoryName().toLowerCase()};
        int[] types = {1};

        when(jdbcTemplateMock.update(anyString(), eq("Food"))).thenReturn(1);
        when(jdbcTemplateMock.queryForObject(anyString(), eq(params), eq(types), any(CategoryRowMapper.class))).thenReturn(category);
        when(jdbcTemplateMock.update(
                anyString(),
                eq("2023-08-12"),
                eq(100.0),
                eq("Expense description"),
                eq(1L),
                eq("Food"))).thenReturn(1);

        // WHEN
        Integer resultId = expenseRepository.insert(expense);

        // THEN
        assertEquals(resultId, 1);
    }

    @Test
    void getAll_ShouldReturnListOfExpenseDto_WhenDataBaseHasData() throws DaoException {
        // GIVEN
        List<Expense> expectedList = List.of(
            new Expense("2023-08-12", 100.0, "Expense description", "Food"),
            new Expense("2023-08-13", 200.0, "Expense description", "Cloth")
        );

        when(jdbcTemplateMock.query(anyString(), any(ExpenseRowMapper.class))).thenReturn(expectedList);

        // WHEN
        List<Expense> resultList = expenseRepository.getAll();

        // THEN
        verify(jdbcTemplateMock).query(anyString(), any(ExpenseRowMapper.class));
        assertEquals(expectedList.size(), resultList.size());
    }
}