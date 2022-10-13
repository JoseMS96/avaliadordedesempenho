package br.fai.add.api.service;

import br.fai.add.model.entities.Collaborator;

import java.util.List;


public interface CollaboratorRestService<T> extends BaseRestService<T> {


    Collaborator validateLogin(String username, String password);

    List<T> findCollaboratorsByForm(int id);


}
