package br.fai.add.api.service.impl;

import br.fai.add.api.service.CollaboratorRestService;
import br.fai.add.db.dao.CollaboratorDao;
import br.fai.add.model.entities.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CollaboratorRestServiceImpl implements CollaboratorRestService<Collaborator> {

    @Autowired
    private CollaboratorDao collaboratorDao;

    @Override
    public List<Collaborator> find() {
        return collaboratorDao.find();
    }

    @Override
    public List<Collaborator> findCollaboratorsByForm(int id) {
        if (id < 0) return null;

        return collaboratorDao.findCollaboratorsByForm(id);
    }

    @Override
    public List<Collaborator> findCollaboratorsByOrganization(int id) {
        if (id < 0) return null;

        return collaboratorDao.findCollaboratorsByOrganization(id);
    }

    @Override
    public Collaborator findById(int id) {
        if (id < 0) return null;

        return (Collaborator) collaboratorDao.findById(id);
    }

    @Override
    public int create(Collaborator entity) {
        return collaboratorDao.create(entity);
    }


    @Override
    public boolean deleteById(int id) {
        return collaboratorDao.deleteById(id);
    }

    @Override
    public Collaborator validateLogin(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }

        if (username.length() < 4 || password.length() < 3) {
            return null;
        }

        return collaboratorDao.validateUsernameAndPassword(username, password);
    }


}
