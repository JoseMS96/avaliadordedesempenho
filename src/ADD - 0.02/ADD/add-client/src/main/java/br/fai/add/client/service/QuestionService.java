package br.fai.add.client.service;

import java.util.List;

public interface QuestionService<T> extends BaseService<T> {

    List<T> findQuestionsByForm(int id);

}
