package com.cristiandev.expensetraking.service.impl;

import com.cristiandev.expensetraking.dto.request.ExpenseRequestDto;
import com.cristiandev.expensetraking.exceptions.DaoException;
import com.cristiandev.expensetraking.repository.ExpenseRepository;
import com.cristiandev.expensetraking.entities.Expense;
import com.cristiandev.expensetraking.service.ExpenseService;
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

    public String createExpense(ExpenseRequestDto expenseRequestDto) throws DaoException {
        Expense expense = new Expense(
                expenseRequestDto.getDate(),
                expenseRequestDto.getAmount(),
                expenseRequestDto.getDescription(),
                expenseRequestDto.getCategoryRequestDto().getName()
        );
        if (expenseRepository.insert(expense).equals(0)) {
            return "The expense could not be saved.";
        } else {
            return "The expense was successfully saved.";
        }
    }

    public Expense getExpenseById(Long id) throws DaoException {
        return expenseRepository.read(id);
    }

    public List<Expense> getAllExpenses() throws DaoException {
        return expenseRepository.getAll();
    }

    public List<Expense> getExpensesByCategory(String categoryName) throws DaoException {
        return expenseRepository.getExpensesByCategory(categoryName);
    }

    public Double getAllExpensesAmount() throws DaoException {
        List<Expense> allExpenses = expenseRepository.getAll();
        ExpenseCalculator expenseCalculator = new ExpenseCalculatorImpl();

        return expenseCalculator.calculateTotalExpenses(allExpenses);
    }
}
