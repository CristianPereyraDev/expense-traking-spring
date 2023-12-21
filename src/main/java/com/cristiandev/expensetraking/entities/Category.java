package com.cristiandev.expensetraking.entities;

public class Category {
    private Long id;
    private String name;

    public Category() {
        this.id = 0L;
    }

    public Category(String name) {
        this.id = 0L;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExpenseCategory{" +
                "name='" + name + '\'' +
                '}';
    }
}
