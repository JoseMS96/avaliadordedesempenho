package br.fai.add.db.dao;

import br.fai.add.model.entities.Collaborator;


public interface UserDao<T> extends BaseDao<T> {

    Collaborator validateUsernameAndPassword(String username, String password);

}
