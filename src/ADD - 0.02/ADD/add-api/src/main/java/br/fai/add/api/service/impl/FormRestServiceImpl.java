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
    public List<Form> findAllForms(int id) {
        if (id < 0) return null;

        return formDao.findAllForms(id);
    }

    @Override
    public List<Form> findAnsweredForms(int id) {
        if (id < 0) return null;

        return formDao.findAnsweredForms(id);
    }

    @Override
    public List<Form> findUnansweredForms(int id) {
        if (id < 0) return null;

        return formDao.findUnansweredForms(id);
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