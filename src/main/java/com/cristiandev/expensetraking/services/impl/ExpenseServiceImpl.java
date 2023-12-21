package com.cristiandev.expensetraking.services.impl;

import com.cristiandev.expensetraking.repository.ExpenseRepository;
import com.cristiandev.expensetraking.dto.ExpenseDto;
import com.cristiandev.expensetraking.entities.Expense;
import com.cristiandev.expensetraking.services.ExpenseService;
import com.cristiandev.expensetraking.utils.ExpenseCalculator;
import com.cristiandev.expensetraking.utils.ExpenseCalculatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
        //this.expenseRepository = new ExpenseRepositoryImplH2(DBConnection.getConnection());
    }

    public Integer createExpense(ExpenseDto expense) {
        return expenseRepository.insert(expense);
    }

    public ExpenseDto getExpenseById(Integer id) {
        return expenseRepository.read(id);
    }

    public List<ExpenseDto> getAllExpenses() {
        return expenseRepository.getAll();
    }

    public List<Expense> getExpensesByCategory(Integer idCategory) {
        return expenseRepository.getExpensesByCategory(idCategory);
    }

    public Double getAllExpensesAmount() {
        List<ExpenseDto> allExpenses = expenseRepository.getAll();
        ExpenseCalculator expenseCalculator = new ExpenseCalculatorImpl();

        return expenseCalculator.calculateTotalExpenses(allExpenses);
    }
}
