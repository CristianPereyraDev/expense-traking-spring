package com.cristiandev.expensetraking.dto.request;

public class ExpenseRequestDto {
    private String date;
    private Double amount;
    private String description;
    private CategoryRequestDto categoryRequestDto;
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
    public CategoryRequestDto getCategoryRequestDto() {
        return categoryRequestDto;
    }
    public void setCategoryRequestDto(CategoryRequestDto categoryRequestDto) {
        this.categoryRequestDto = categoryRequestDto;
    }
}
