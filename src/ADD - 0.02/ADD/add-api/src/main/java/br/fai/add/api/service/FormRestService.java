package br.fai.add.api.service;

import java.util.List;

public interface FormRestService<T> {

    List<T> findAllForms(int id);

    List<T> findAnsweredForms(int id);

    List<T> findUnansweredForms(int id);

    T findById(int id);

    int create(T entity);

    boolean deleteById(int id);

}
