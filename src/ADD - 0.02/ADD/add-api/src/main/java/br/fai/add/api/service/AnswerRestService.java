package br.fai.add.api.service;

public interface AnswerRestService<T> extends BaseRestService<T> {

    T findAnswerByQuestion(int id);
    
}
