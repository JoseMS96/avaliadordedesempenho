package br.fai.add.client.service;

import br.fai.add.model.entities.UserModel;

public interface UserService<T> extends BaseService<T> {


    UserModel validateUsernameAndPassword(String username, String password);

}
