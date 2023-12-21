package com.cristiandev.expensetraking.repository;

import java.util.List;
import com.cristiandev.expensetraking.dto.ExpenseDto;
import com.cristiandev.expensetraking.entities.Expense;

public interface ExpenseRepository extends Repository<Expense, Long> {
    List<Expense> getExpensesByCategory(String categoryName);
}
