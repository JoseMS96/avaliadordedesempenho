package br.fai.add.client.service.impl;

import br.fai.add.client.service.OptionService;
import br.fai.add.client.service.RestService;
import br.fai.add.model.entities.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService<Option> {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api/";

    private String buildEndpoint(String resource) {
        return BASE_ENDPOINT + resource;
    }

    @Autowired
    private RestService<Option> restService;

    @Override
    public int create(Option entity) {

        if (entity.getDescription() == "" || entity.getDescription() == null) {
            return -1;
        }

        return restService.post("option/create", entity);
    }

    @Override
    public List<Option> find() {

        return restService.get("option/find");
    }

    @Override
    public List<Option> findOptionsByQuestion(int id) {
        return restService.getListById("option/findOptionsByQuestion/" + id);
    }

    @Override
    public Option findOptionByQuestion(int id, int id2) {
        return restService.getById("answer/findOptionByQuestion/" + id + "/" + id2, Option.class);
    }

    @Override
    public Option findById(int id) {

        return restService.getById("option/find/" + id, Option.class);
    }

    @Override
    public boolean deleteById(int id) {

        return restService.deleteById("option/delete/" + id);
    }


}