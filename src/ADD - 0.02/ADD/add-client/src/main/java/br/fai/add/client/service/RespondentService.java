package br.fai.add.client.service;

import java.util.List;

public interface RespondentService<T> extends BaseService<T> {

    List<T> findRespondentsByForm(int id);

    List<T> findRespondentsByOrg(int id);

    T findRespondentByCollaboratorAndForm(int id, int id2);

    boolean update(int id, T entity);

}
