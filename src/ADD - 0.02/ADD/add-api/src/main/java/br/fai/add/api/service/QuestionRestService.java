package br.fai.add.api.service;

import java.util.List;

public interface QuestionRestService<T> extends BaseRestService<T> {

    List<T> findQuestionsByForm(int id);

}
