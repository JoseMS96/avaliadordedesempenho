package br.fai.add.api.service.impl;

import br.fai.add.api.service.UserRestService;
import br.fai.add.db.dao.UserDao;
import br.fai.add.model.entities.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRestServiceImpl implements UserRestService<Collaborator> {

    @Autowired
    private UserDao userDao;

    @Override
    public List<Collaborator> find() {
        return userDao.find();
    }

    @Override
    public Collaborator findById(int id) {
        if (id < 0) return null;

        return (Collaborator) userDao.findById(id);
    }

    @Override
    public int create(Collaborator entity) {
        return userDao.create(entity);
    }


    @Override
    public boolean deleteById(int id) {
        return userDao.deleteById(id);
    }

    @Override
    public Collaborator validateLogin(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }

        if (username.length() < 4 || password.length() < 3) {
            return null;
        }

        return userDao.validateUsernameAndPassword(username, password);
    }
}
