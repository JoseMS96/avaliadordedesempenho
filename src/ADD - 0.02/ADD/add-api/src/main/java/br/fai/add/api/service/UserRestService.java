package br.fai.add.api.service;

import br.fai.add.model.entities.Colaborator;

public interface UserRestService<T> extends BaseRestService<T> {


    Colaborator validateLogin(String username, String password);


}
