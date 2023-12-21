package com.cristiandev.expensetraking.utils;

import com.cristiandev.expensetraking.entities.Expense;

import java.util.List;

public interface ExpenseCalculator {
    double calculateTotalExpenses(List<Expense> expenses);
}
