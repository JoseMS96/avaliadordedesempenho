package br.fai.add.client.service;

import br.fai.add.model.entities.Collaborator;

public interface CollaboratorService<T> extends BaseService<T> {


    Collaborator validateUsernameAndPassword(String username, String password);

}
