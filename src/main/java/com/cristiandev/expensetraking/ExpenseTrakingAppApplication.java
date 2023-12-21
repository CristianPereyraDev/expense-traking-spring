package com.cristiandev.expensetraking;

import com.cristiandev.expensetraking.services.impl.ExpenseServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrakingAppApplication {
	private final ExpenseServiceImpl expenseService;
	@Autowired
	public ExpenseTrakingAppApplication(ExpenseServiceImpl expenseService) {
		this.expenseService = expenseService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrakingAppApplication.class, args);
	}

	@PostConstruct
	private void executeExample() {
		//Double totalExpensesAmount = expenseService.getAllExpensesAmount();
		//System.out.println("Total expenses amount = " + totalExpensesAmount);
	}

}
