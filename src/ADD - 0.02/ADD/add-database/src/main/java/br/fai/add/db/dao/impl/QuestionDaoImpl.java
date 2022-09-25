package br.fai.add.db.dao.impl;

import br.fai.add.db.dao.QuestionDao;
import br.fai.add.model.entities.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao<Question> {

    @Override
    public List<Question> find() {
        return null;
    }

    @Override
    public Question findById(int id) {
        return null;
    }

    @Override
    public int create(Question entity) {
        return -1;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
