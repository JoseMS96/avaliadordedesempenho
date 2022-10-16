package br.fai.add.client.service;

public interface AnswerService<T> extends BaseService<T> {

    T findAnswerByQuestion(int id);

    T findAnswerByQuestionAndCollaborator(int id, int id2);

}
