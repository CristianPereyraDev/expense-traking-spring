package com.cristiandev.expensetraking.repository;

import com.cristiandev.expensetraking.exceptions.DaoException;

import java.util.List;

public interface Repository<T, K> {
    Integer insert(T t) throws DaoException;
    T read(K id) throws DaoException;
    Integer update(T t) throws DaoException;
    Integer delete(K id) throws DaoException;
    List<T> getAll() throws DaoException;
}
