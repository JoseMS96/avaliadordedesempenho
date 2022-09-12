package br.fai.add.api.service.impl;

import br.fai.add.api.service.UserRestService;
import br.fai.add.db.dao.UserDao;
import br.fai.add.model.entities.Colaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRestServiceImpl implements UserRestService<Colaborator> {

    @Autowired
    private UserDao userDao;

    @Override
    public List<Colaborator> find() {
        return userDao.find();
    }

    @Override
    public Colaborator findById(int id) {
        if (id < 0) return null;

        return (Colaborator) userDao.findById(id);
    }

    @Override
    public int create(Colaborator entity) {
        return 0;
    }

    @Override
    public boolean update(Colaborator entity) {
        return false;
    }


    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public Colaborator validateLogin(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }

        if (username.length() < 4 || password.length() < 3) {
            return null;
        }

        return userDao.validateUsernameAndPassword(username, password);
    }
}
