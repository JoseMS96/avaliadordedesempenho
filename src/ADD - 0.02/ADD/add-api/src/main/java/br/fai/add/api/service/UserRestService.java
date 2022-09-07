package br.fai.add.api.service;

import br.fai.add.model.entities.ColaboratorModel;

public interface UserRestService<T> extends BaseRestService<T> {


    ColaboratorModel validateLogin(String username, String password);


}
