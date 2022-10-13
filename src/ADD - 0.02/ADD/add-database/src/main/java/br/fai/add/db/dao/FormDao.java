package br.fai.add.db.dao;

import java.util.List;

public interface FormDao<T> extends BaseDao<T> {

    List<T> findAllForms(int id);

    List<T> findAnsweredForms(int id);

    List<T> findUnansweredForms(int id);

}
