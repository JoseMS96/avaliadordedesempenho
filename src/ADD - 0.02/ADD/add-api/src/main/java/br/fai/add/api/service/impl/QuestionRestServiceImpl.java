package br.fai.add.api.service.impl;

import br.fai.add.api.service.QuestionRestService;
import br.fai.add.db.dao.QuestionDao;
import br.fai.add.model.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionRestServiceImpl implements QuestionRestService<Question> {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public List<Question> find() {
        return questionDao.find();
    }

    @Override
    public List<Question> findQuestionsByForm(int id) {
        if (id < 0) return null;

        return questionDao.findQuestionsByForm(id);
    }

    @Override
    public Question findById(int id) {
        if (id < 0) return null;

        return (Question) questionDao.findById(id);
    }

    @Override
    public int create(Question entity) {
        return questionDao.create(entity);
    }


    @Override
    public boolean deleteById(int id) {
        return questionDao.deleteById(id);
    }

}
