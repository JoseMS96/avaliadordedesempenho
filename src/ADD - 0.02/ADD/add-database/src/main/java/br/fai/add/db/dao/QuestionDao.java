package br.fai.add.db.dao;

import java.util.List;

public interface QuestionDao<T> extends BaseDao<T> {

    List<T> findQuestionsByForm(int id);

}
