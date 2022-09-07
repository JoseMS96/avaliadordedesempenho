package br.fai.add.client.service.impl;

import br.fai.add.client.service.RestService;
import br.fai.add.client.service.UserService;
import br.fai.add.model.entities.ColaboratorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService<ColaboratorModel> {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api/";

    private String buildEndpoint(String resource) {
        return BASE_ENDPOINT + resource;
    }

    @Autowired
    private RestService restService;

    @Override
    public int create(ColaboratorModel entity) {
        return 0;
    }

    @Override
    public List<ColaboratorModel> find() {

        return null;
    }

    @Override
    public ColaboratorModel findById(int id) {

        return null;
    }

    @Override
    public boolean update(ColaboratorModel entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public ColaboratorModel validateUsernameAndPassword(String username, String password) {

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> httpEntity = new HttpEntity<>("");

        String resource = "account/login?username=" + username + "&password=" + password;


        ResponseEntity<ColaboratorModel> responseEntity = restTemplate.exchange(buildEndpoint(resource), HttpMethod.POST, httpEntity, ColaboratorModel.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return null;
        }

        ColaboratorModel user = responseEntity.getBody();

        return user;
    }
}
