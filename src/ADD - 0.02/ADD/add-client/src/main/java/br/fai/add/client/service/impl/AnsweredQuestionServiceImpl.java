package br.fai.add.client.service.impl;

import br.fai.add.client.service.RestService;
import br.fai.add.model.entities.AnsweredQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnsweredQuestionServiceImpl {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api/";

    private String buildEndpoint(String resource) {
        return BASE_ENDPOINT + resource;
    }

    @Autowired
    private RestService<AnsweredQuestion> restService;

    public List<AnsweredQuestion> find(int formId, int collaboratorId) {

        return restService.get("answeredQuestion/find/" + formId + "/" + collaboratorId);

    }


}
