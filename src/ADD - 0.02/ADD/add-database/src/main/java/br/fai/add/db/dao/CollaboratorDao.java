package br.fai.add.db.dao;

import br.fai.add.model.entities.Collaborator;

import java.util.List;


public interface CollaboratorDao<T> extends BaseDao<T> {

    Collaborator validateUsernameAndPassword(String username, String password);

    List<T> findCollaboratorsByForm(int id);

}
