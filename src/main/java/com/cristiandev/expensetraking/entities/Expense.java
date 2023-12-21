package com.cristiandev.expensetraking.entities;

public class Expense {
    private Long id;
    private String date;
    private Double amount;
    private String description;
    private Long categoryId;
    private String categoryName;

    public Expense() {
        this.amount = 0.0;
    }

    public Expense(String date, Double amount, String description, String categoryName) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
