package br.fai.add.api.service.impl;

import br.fai.add.api.service.OptionRestService;
import br.fai.add.db.dao.OptionDao;
import br.fai.add.model.entities.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionRestServiceImpl implements OptionRestService<Option> {

    @Autowired
    private OptionDao optionDao;

    @Override
    public List<Option> find() {
        return optionDao.find();
    }

    @Override
    public List<Option> findOptionsByQuestion(int id) {
        if (id < 0) return null;

        return optionDao.findOptionsByQuestion(id);
    }

    @Override
    public Option findById(int id) {
        if (id < 0) return null;

        return (Option) optionDao.findById(id);
    }

    @Override
    public int create(Option entity) {
        return optionDao.create(entity);
    }


    @Override
    public boolean deleteById(int id) {
        return optionDao.deleteById(id);
    }


}
