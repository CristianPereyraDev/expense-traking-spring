package com.cristiandev.expensetraking.controller;

import com.cristiandev.expensetraking.dto.request.CategoryRequestDto;
import com.cristiandev.expensetraking.dto.request.ExpenseRequestDto;
import com.cristiandev.expensetraking.entities.Expense;
import com.cristiandev.expensetraking.exceptions.DaoException;
import com.cristiandev.expensetraking.service.ExpenseService;
import com.cristiandev.expensetraking.utils.ExpenseCalculator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseCalculator expenseCalculator;
    public ExpenseController(ExpenseService expenseService, ExpenseCalculator expenseCalculator) {
        this.expenseService = expenseService;
        this.expenseCalculator = expenseCalculator;
    }

    @PostMapping()
    public ResponseEntity<String> createExpenseHandler(@RequestBody ExpenseRequestDto expenseRequestDto) throws DaoException {
        String response = expenseService.createExpense(expenseRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/allExpenses")
    ResponseEntity<List<Expense>> allExpensesHandler() throws DaoException {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/allExpensesByCategory")
    ResponseEntity<List<Expense>> allExpensesByCategoryHandler(@RequestBody CategoryRequestDto categoryRequestDto) throws DaoException {
        List<Expense> response = expenseService.getExpensesByCategory(categoryRequestDto.getName());
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/totalExpenses")
    ResponseEntity<Double> totalExpensesHandler() throws DaoException {
        List<Expense> expenseList = expenseService.getAllExpenses();

        return ResponseEntity.status(HttpStatus.FOUND).body(expenseCalculator.calculateTotalExpenses(expenseList));
    }
}
