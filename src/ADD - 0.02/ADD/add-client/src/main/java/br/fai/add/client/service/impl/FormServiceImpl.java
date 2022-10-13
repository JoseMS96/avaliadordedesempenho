package br.fai.add.client.service.impl;

import br.fai.add.client.service.FormService;
import br.fai.add.client.service.RestService;
import br.fai.add.model.entities.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormServiceImpl implements FormService<Form> {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api/";

    private String buildEndpoint(String resource) {
        return BASE_ENDPOINT + resource;
    }

    @Autowired
    private RestService<Form> restService;

    @Override
    public int create(Form entity) {
        return restService.post("form/create", entity);
    }

    @Override
    public List<Form> findAllForms(int id) {
        return restService.getListById("form/findAllForms/" + id);
    }

    @Override
    public List<Form> findAnsweredForms(int id) {
        return restService.getListById("form/findAnsweredForms/" + id);
    }

    @Override
    public List<Form> findUnansweredForms(int id) {
        return restService.getListById("form/findUnansweredForms/" + id);
    }


    @Override
    public Form findById(int id) {
        return restService.getById("form/find/" + id, Form.class);
    }

    @Override
    public boolean deleteById(int id) {
        return restService.deleteById("form/delete/" + id);
    }

}