package br.fai.add.api.service;

import java.util.List;

public interface BaseRestService<T> {

    List<T> find();

    T findById(int id);

    int create(T entity);

    boolean deleteById(int id);

}
