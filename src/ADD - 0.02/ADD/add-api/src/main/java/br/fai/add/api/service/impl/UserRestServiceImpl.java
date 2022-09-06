package br.fai.add.api.service.impl;

import br.fai.add.api.service.UserRestService;
import br.fai.add.db.dao.UserDao;
import br.fai.add.model.entities.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRestServiceImpl implements UserRestService<UserModel> {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserModel> find() {
        return userDao.find();
    }

    @Override
    public UserModel findById(int id) {
        if (id < 0) return null;

        return (UserModel) userDao.findById(id);
    }

    @Override
    public int create(UserModel entity) {
        return 0;
    }

    @Override
    public boolean update(UserModel entity) {
        return false;
    }


    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public UserModel validateLogin(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }

        if (username.length() < 4 || password.length() < 3) {
            return null;
        }

        return userDao.validateUsernameAndPassword(username, password);
    }
}
