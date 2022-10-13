package br.fai.add.client.service;

import br.fai.add.model.entities.Collaborator;

import java.util.List;

public interface CollaboratorService<T> extends BaseService<T> {

    Collaborator validateUsernameAndPassword(String username, String password);

    List<T> findCollaboratorsByForm(int id);

}
