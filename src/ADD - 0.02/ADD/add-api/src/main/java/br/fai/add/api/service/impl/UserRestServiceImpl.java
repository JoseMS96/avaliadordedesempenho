package br.fai.add.api.service.impl;

import br.fai.add.api.service.UserRestService;
import br.fai.add.db.dao.UserDao;
import br.fai.add.model.entities.ColaboratorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRestServiceImpl implements UserRestService<ColaboratorModel> {

    @Autowired
    private UserDao userDao;

    @Override
    public List<ColaboratorModel> find() {
        return userDao.find();
    }

    @Override
    public ColaboratorModel findById(int id) {
        if (id < 0) return null;

        return (ColaboratorModel) userDao.findById(id);
    }

    @Override
    public int create(ColaboratorModel entity) {
        return 0;
    }

    @Override
    public boolean update(ColaboratorModel entity) {
        return false;
    }


    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public ColaboratorModel validateLogin(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }

        if (username.length() < 4 || password.length() < 3) {
            return null;
        }

        return userDao.validateUsernameAndPassword(username, password);
    }
}
