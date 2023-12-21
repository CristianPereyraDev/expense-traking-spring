package com.cristiandev.expensetraking.repository.mappers;

import com.cristiandev.expensetraking.entities.Expense;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseRowMapper implements RowMapper<Expense> {
    @Override
    public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
        Expense expense = new Expense();
        expense.setId(rs.getLong("id"));
        expense.setDate(rs.getString("date"));
        expense.setAmount(rs.getDouble("amount"));
        expense.setDescription(rs.getString("description"));
        expense.setCategoryName(rs.getString("category_name"));
        expense.setCategoryId(rs.getLong("category_id"));
        return expense;
    }
}
