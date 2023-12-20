package com.cristiandev.expensetraking.utils;

import com.cristiandev.expensetraking.exceptions.InvalidExpenseException;

public class ExpenseAmountValidatorImpl implements ExpenseAmountValidator {
    @Override
    public boolean validateAmount(double amount) throws InvalidExpenseException {
        if (amount < 0) {
            throw new InvalidExpenseException("Invalid amount");
        } else {
            return true;
        }
    }
}
