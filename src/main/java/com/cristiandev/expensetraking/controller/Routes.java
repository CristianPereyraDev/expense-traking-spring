package com.cristiandev.expensetraking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Routes {
    @GetMapping("/allExpenses")
    String allExpenses() { return "Hi, it is my first route"; }
}
