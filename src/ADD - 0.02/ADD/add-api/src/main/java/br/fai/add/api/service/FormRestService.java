package br.fai.add.api.service;

import java.util.List;

public interface FormRestService<T> extends BaseRestService<T> {

    List<T> findAllForms(int id);

    List<T> findAnsweredForms(int id);

    List<T> findUnansweredForms(int id);


}
