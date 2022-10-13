package br.fai.add.client.service;

import java.util.List;

public interface FormService<T> {

    int create(T entity);

    List<T> findAllForms(int id);

    List<T> findAnsweredForms(int id);

    List<T> findUnansweredForms(int id);

    T findById(int id);

    boolean deleteById(int id);


}
