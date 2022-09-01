package br.fai.add.client.service;

import java.util.List;

//generics
public interface BaseService<T> {

    int create(T entity);

    List<T> find();

    T findById(int id);

    boolean update(T entity);

    boolean deleteById(int id);

}
