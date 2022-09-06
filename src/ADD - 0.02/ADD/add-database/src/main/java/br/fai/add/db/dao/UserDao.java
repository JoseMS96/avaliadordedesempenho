package br.fai.add.db.dao;

import br.fai.lds.model.entities.UserModel;

// DAO = DATA ACESS OBJECT
public interface UserDao<T> extends BaseDao<T> {

    UserModel validateUsernameAndPassword(String username, String password);

}
