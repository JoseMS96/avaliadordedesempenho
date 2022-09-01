package br.fai.add.api.service;

import br.fai.add.model.entities.UserModel;

public interface UserRestService<T> extends BaseRestService<T> {


    UserModel validateLogin(String username, String password);


}
