package br.fai.add.db.dao.impl;

import br.fai.add.db.dao.OptionDao;
import br.fai.add.model.entities.Option;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionDaoImpl implements OptionDao<Option> {

    @Override
    public List<Option> find() {
        return null;
    }

    @Override
    public Option findById(int id) {
        return null;
    }

    @Override
    public int create(Option entity) {
        return -1;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}