package br.fai.add.api.service;

import br.fai.add.model.entities.Collaborator;


public interface CollaboratorRestService<T> extends BaseRestService<T> {


    Collaborator validateLogin(String username, String password);


}