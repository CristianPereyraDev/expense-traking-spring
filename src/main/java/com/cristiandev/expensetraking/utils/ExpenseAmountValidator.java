package com.cristiandev.expensetraking.utils;

import com.cristiandev.expensetraking.exceptions.InvalidExpenseException;

@FunctionalInterface
public interface ExpenseAmountValidator {
    boolean validateAmount(double amount) throws InvalidExpenseException;
}
