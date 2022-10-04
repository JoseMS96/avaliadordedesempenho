package br.fai.add.db.dao.impl;

import br.fai.add.db.dao.AnswerDao;
import br.fai.add.model.entities.Answer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswerDaoImpl implements AnswerDao<Answer> {

    @Override
    public List<Answer> find() {
        return null;
    }

    @Override
    public Answer findById(int id) {
        return null;
    }

    @Override
    public int create(Answer entity) {
        return -1;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
