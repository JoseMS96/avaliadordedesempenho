package br.fai.add.db.dao.impl;

import br.fai.add.db.dao.FormDao;
import br.fai.add.model.entities.Form;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FormDaoImpl implements FormDao<Form> {

    @Override
    public List<Form> find() {
        return null;
    }

    @Override
    public Form findById(int id) {
        return null;
    }

    @Override
    public int create(Form entity) {
        return -1;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
