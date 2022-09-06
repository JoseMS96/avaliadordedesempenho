package br.fai.add.db.dao;

import java.util.List;

// DAO = DATA ACESS OBJECT
public interface BaseDao<T> {

    List<T> find();

    T findById(int id);

    int create(T entity);

    boolean update(T entity);

    boolean deleteById(int id);

}
