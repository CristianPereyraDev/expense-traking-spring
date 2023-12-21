package com.cristiandev.expensetraking.utils;

import com.cristiandev.expensetraking.entities.Expense;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ExpenseCalculatorImpl implements ExpenseCalculator {
    @Override
    public double calculateTotalExpenses(List<Expense> expenses) {
        double totalExpense = 0;

        for (Expense expense: expenses) {
            totalExpense += expense.getAmount();
        }

        return totalExpense;
    }
}
