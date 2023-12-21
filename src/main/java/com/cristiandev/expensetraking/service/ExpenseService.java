package com.cristiandev.expensetraking.service;

import com.cristiandev.expensetraking.dto.ExpenseDto;
import com.cristiandev.expensetraking.dto.request.ExpenseRequestDto;
import com.cristiandev.expensetraking.entities.Expense;
import com.cristiandev.expensetraking.exceptions.DaoException;

import java.util.List;

public interface ExpenseService {
    String createExpense(ExpenseRequestDto expense) throws DaoException;
    Expense getExpenseById(Long id) throws DaoException;
    List<Expense> getAllExpenses() throws DaoException;
    List<Expense> getExpensesByCategory(String categoryName) throws DaoException;
    Double getAllExpensesAmount() throws DaoException;
}
