package br.fai.add.api.service.impl;

import br.fai.add.api.service.AnswerRestService;
import br.fai.add.db.dao.AnswerDao;
import br.fai.add.model.entities.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerRestServiceImpl implements AnswerRestService<Answer> {

    @Autowired
    private AnswerDao answerDao;

    @Override
    public List<Answer> find() {
        return answerDao.find();
    }

    @Override
    public Answer findById(int id) {
        if (id < 0) return null;

        return (Answer) answerDao.findById(id);
    }

    @Override
    public int create(Answer entity) {
        return answerDao.create(entity);
    }


    @Override
    public boolean deleteById(int id) {
        return answerDao.deleteById(id);
    }


}
