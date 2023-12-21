package com.cristiandev.expensetraking.services;

import com.cristiandev.expensetraking.dto.ExpenseDto;
import com.cristiandev.expensetraking.entities.Expense;

import java.util.List;

public interface ExpenseService {
    Integer createExpense(ExpenseDto expense);
    ExpenseDto getExpenseById(Integer id);
    List<ExpenseDto> getAllExpenses();
    List<Expense> getExpensesByCategory(Integer idCategory);
    Double getAllExpensesAmount();
}
