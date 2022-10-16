package br.fai.add.db.dao;

import java.util.List;

public interface RespondentDao<T> extends BaseDao<T> {

    List<T> findRespondentsByForm(int id);

    List<T> findRespondentsByOrg(int id);

    T findRespondentByCollaboratorAndForm(int id, int id2);

    boolean update(T entity, int id);

}
