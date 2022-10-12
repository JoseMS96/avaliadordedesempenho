package br.fai.add.db.dao;

import java.util.List;

public interface FormDao<T> {
    List<T> findAnsweredForms();

    List<T> findUnansweredForms();

    T findById(int id);

    int create(T entity);

    boolean deleteById(int id);
}
