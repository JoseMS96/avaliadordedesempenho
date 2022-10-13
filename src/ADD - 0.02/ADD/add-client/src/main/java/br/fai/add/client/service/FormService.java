package br.fai.add.client.service;

import java.util.List;

public interface FormService<T> extends BaseService<T> {

    List<T> findAllForms(int id);

    List<T> findAnsweredForms(int id);

    List<T> findUnansweredForms(int id);

}
