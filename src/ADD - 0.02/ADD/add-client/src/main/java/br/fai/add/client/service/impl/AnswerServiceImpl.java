package br.fai.add.client.service.impl;

import br.fai.add.client.service.AnswerService;
import br.fai.add.client.service.RestService;
import br.fai.add.model.entities.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService<Answer> {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api/";

    private String buildEndpoint(String resource) {
        return BASE_ENDPOINT + resource;
    }

    @Autowired
    private RestService<Answer> restService;

    @Override
    public int create(Answer entity) {

        return restService.post("answer/create", entity);
    }

    @Override
    public List<Answer> find() {

        return restService.get("answer/find");
    }

    @Override
    public Answer findById(int id) {

        return restService.getById("answer/find/" + id, Answer.class);
    }

    @Override
    public boolean deleteById(int id) {

        return restService.deleteById("answer/delete/" + id);
    }

}