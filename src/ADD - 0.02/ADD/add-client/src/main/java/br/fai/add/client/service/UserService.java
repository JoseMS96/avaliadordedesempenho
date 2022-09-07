package br.fai.add.client.service;

import br.fai.add.model.entities.ColaboratorModel;

public interface UserService<T> extends BaseService<T> {


    ColaboratorModel validateUsernameAndPassword(String username, String password);

}
