package br.fai.add.client.service;

import java.util.List;

public interface RespondentService<T> extends BaseService<T> {

    List<T> findRespondentsByForm(int id);

}
