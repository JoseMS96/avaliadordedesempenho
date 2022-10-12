package br.fai.add.api.service;

import java.util.List;

public interface FormRestService<T> {

    List<T> findAnsweredForms();

    List<T> findUnansweredForms();

    T findById(int id);

    int create(T entity);

    boolean deleteById(int id);

}
