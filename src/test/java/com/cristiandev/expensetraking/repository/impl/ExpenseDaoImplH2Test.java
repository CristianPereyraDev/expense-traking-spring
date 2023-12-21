package com.cristiandev.expensetraking.repository.impl;

import com.cristiandev.expensetraking.entities.Expense;
import com.cristiandev.expensetraking.exceptions.DaoException;
import com.cristiandev.expensetraking.repository.ExpenseRepository;
import com.cristiandev.expensetraking.dto.ExpenseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExpenseDaoImplH2Test {

    @Mock
    private Connection connectionMock;
    @Mock
    private PreparedStatement preparedStatementMock;
    @Mock
    private ResultSet resultSetMock;
    private ExpenseRepository expenseRepository;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        //expenseDao = new ExpenseRepositoryImplH2(connectionMock);
    }

    @Test
    @DisplayName("Given a valid ExpenseDto, insert should be add a new register.")
    void insert_ShouldInsertExpense_WhenValidExpenseDto() throws SQLException, DaoException {
        // GIVEN
        Expense expense = new Expense();
        expense.setDate("2023-08-12");
        expense.setAmount(100.0);
        expense.setDescription("Expense description");
        expense.setCategoryName("Food");

        when(connectionMock.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(preparedStatementMock.getGeneratedKeys()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt(1)).thenReturn(1);

        // WHEN
        Integer resultId = expenseRepository.insert(expense);

        // THEN
        verify(preparedStatementMock).setString(1, expense.getDate());
        verify(preparedStatementMock).setDouble(2, expense.getAmount());
        verify(preparedStatementMock).setString(3, expense.getDescription());
        verify(preparedStatementMock).setString(4, expense.getCategoryName());
        verify(preparedStatementMock, times(1)).executeUpdate();

        assertEquals(resultId, 1);
    }

    @Test
    void getAll_ShouldReturnListOfExpenseDto_WhenDataBaseHasData() throws SQLException, DaoException {
        // GIVEN
        List<ExpenseDto> expectedList = List.of(
            new ExpenseDto("2023-08-12", 100.0, "Expense description", 2),
            new ExpenseDto("2023-08-13", 200.0, "Expense description", 3)
        );

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true, true, false);
        when(resultSetMock.getInt("id")).thenReturn(1, 2);
        when(resultSetMock.getString("date")).thenReturn("2023-08-12", "2023-08-13");
        when(resultSetMock.getDouble("amount")).thenReturn(100.0, 200.0);
        when(resultSetMock.getString("description")).thenReturn("2Expense description", "Expense description");
        when(resultSetMock.getInt("categoryId")).thenReturn(2, 3);

        // WHEN
        List<Expense> resultList = expenseRepository.getAll();

        // THEN
        verify(preparedStatementMock).executeQuery();
        verify(resultSetMock, times(2)).getInt("id");
        verify(resultSetMock, times(2)).getString("date");
        verify(resultSetMock, times(2)).getDouble("amount");
        verify(resultSetMock, times(2)).getString("description");
        verify(resultSetMock, times(2)).getInt("categoryId");
        assertEquals(expectedList.size(), resultList.size());
    }
}