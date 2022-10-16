package br.fai.add.db.dao;

import java.util.List;

public interface OptionDao<T> extends BaseDao<T> {

    List<T> findOptionsByQuestion(int id);

    T findOptionByQuestion(int id, int id2);

}
