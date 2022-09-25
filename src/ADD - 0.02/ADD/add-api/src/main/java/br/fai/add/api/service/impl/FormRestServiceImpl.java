package br.fai.add.api.service.impl;

import br.fai.add.api.service.FormRestService;
import br.fai.add.db.dao.FormDao;
import br.fai.add.model.entities.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormRestServiceImpl implements FormRestService<Form> {

    @Autowired
    private FormDao formDao;

    @Override
    public List<Form> find() {
        return formDao.find();
    }

    @Override
    public Form findById(int id) {
        if (id < 0) return null;

        return (Form) formDao.findById(id);
    }

    @Override
    public int create(Form entity) {
        return formDao.create(entity);
    }


    @Override
    public boolean deleteById(int id) {
        return formDao.deleteById(id);
    }

}