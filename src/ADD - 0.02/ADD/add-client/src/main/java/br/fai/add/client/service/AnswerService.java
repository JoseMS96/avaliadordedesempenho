package br.fai.add.client.service;

public interface AnswerService<T> extends BaseService<T> {

    T findAnswerByQuestion(int id);

}
