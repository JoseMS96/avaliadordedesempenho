package br.fai.add.db.dao;

public interface AnswerDao<T> extends BaseDao<T> {

    T findAnswerByQuestion(int id);

}
