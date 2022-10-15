package br.fai.add.client.service.impl;

import br.fai.add.client.service.RespondentService;
import br.fai.add.client.service.RestService;
import br.fai.add.model.entities.Respondent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespondentServiceImpl implements RespondentService<Respondent> {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api/";

    private String buildEndpoint(String resource) {
        return BASE_ENDPOINT + resource;
    }

    @Autowired
    private RestService<Respondent> restService;

    @Override
    public int create(Respondent entity) {

        return restService.post("respondent/create", entity);
    }

    @Override
    public List<Respondent> find() {

        return restService.get("respondent/find");
    }

    @Override
    public List<Respondent> findRespondentsByForm(int id) {
        return restService.getListById("respondent/findRespondentsByForm/" + id);
    }

    @Override
    public List<Respondent> findRespondentsByOrg(int id) {
        return restService.getListById("respondent/findRespondentsByOrg/" + id);
    }

    @Override
    public Respondent findById(int id) {

        return restService.getById("respondent/find/" + id, Respondent.class);
    }

    @Override
    public boolean deleteById(int id) {

        return restService.deleteById("respondent/delete/" + id);
    }

}
