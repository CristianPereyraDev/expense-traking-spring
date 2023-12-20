package com.cristiandev.expensetraking.utils;

import com.cristiandev.expensetraking.dto.ExpenseDto;

import java.util.List;

public interface ExpenseCalculator {
    double calculateTotalExpenses(List<ExpenseDto> expenses);
}
