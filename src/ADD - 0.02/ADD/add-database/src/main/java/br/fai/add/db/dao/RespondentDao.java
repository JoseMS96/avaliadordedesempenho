package br.fai.add.db.dao;

import java.util.List;

public interface RespondentDao<T> extends BaseDao<T> {

    List<T> findRespondentsByForm(int id);

}
