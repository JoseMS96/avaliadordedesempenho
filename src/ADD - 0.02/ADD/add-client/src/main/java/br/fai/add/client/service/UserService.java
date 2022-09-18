package br.fai.add.client.service;

import br.fai.add.model.entities.Collaborator;

public interface UserService<T> extends BaseService<T> {


    Collaborator validateUsernameAndPassword(String username, String password);

}
