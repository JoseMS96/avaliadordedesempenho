package br.fai.add.client.service.impl;

import br.fai.add.client.service.QuestionService;
import br.fai.add.client.service.RestService;
import br.fai.add.model.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService<Question> {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api/";

    private String buildEndpoint(String resource) {
        return BASE_ENDPOINT + resource;
    }

    @Autowired
    private RestService<Question> restService;

    @Override
    public int create(Question entity) {

        if (entity.getDescription() == null) {
            return -1;
        }

        return restService.post("question/create", entity);
    }

    @Override
    public List<Question> find() {

        return restService.get("question/find");
    }

    @Override
    public List<Question> findQuestionsByForm(int id) {
        return restService.getListById("question/findQuestionsByForm/" + id);
    }

    @Override
    public Question findById(int id) {

        return restService.getById("question/find/" + id, Question.class);
    }

    @Override
    public boolean deleteById(int id) {

        return restService.deleteById("question/delete/" + id);
    }

}
