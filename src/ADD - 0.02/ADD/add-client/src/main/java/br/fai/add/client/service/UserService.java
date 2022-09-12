package br.fai.add.client.service;

import br.fai.add.model.entities.Colaborator;

public interface UserService<T> extends BaseService<T> {


    Colaborator validateUsernameAndPassword(String username, String password);

}
