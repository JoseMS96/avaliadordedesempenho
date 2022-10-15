package br.fai.add.api.service;

import java.util.List;

public interface RespondentRestService<T> extends BaseRestService<T> {

    List<T> findRespondentsByForm(int id);

}
