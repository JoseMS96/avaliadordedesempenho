package br.fai.add.client.service;

import java.util.List;

public interface FormService<T> {

    int create(T entity);

    List<T> findAnsweredForms();

    List<T> findUnansweredForms();

    T findById(int id);

    boolean deleteById(int id);


}
