package com.cristiandev.expensetraking.services;

import com.cristiandev.expensetraking.config.DBConnection;
import com.cristiandev.expensetraking.repository.ExpenseRepository;
import com.cristiandev.expensetraking.dto.ExpenseDto;
import com.cristiandev.expensetraking.repository.impl.ExpenseRepositoryImplH2;
import com.cristiandev.expensetraking.entities.Expense;
import com.cristiandev.expensetraking.utils.ExpenseCalculator;
import com.cristiandev.expensetraking.utils.ExpenseCalculatorImpl;

import java.util.List;

public class ExpenseService {
    private final ExpenseRepository expenseDao;

    public ExpenseService() {
        expenseDao = new ExpenseRepositoryImplH2(DBConnection.getConnection());
    }

    public Integer createExpense(ExpenseDto expense) {
        return expenseDao.insert(expense);
    }

    public ExpenseDto getExpenseById(Integer id) {
        return expenseDao.read(id);
    }

    public List<ExpenseDto> getAllExpenses() {
        return expenseDao.getAll();
    }

    public List<Expense> getExpensesByCategory(Integer idCategory) {
        return expenseDao.getExpensesByCategory(idCategory);
    }

    public Double getAllExpensesAmount() {
        List<ExpenseDto> allExpenses = expenseDao.getAll();
        ExpenseCalculator expenseCalculator = new ExpenseCalculatorImpl();

        return expenseCalculator.calculateTotalExpenses(allExpenses);
    }
}
