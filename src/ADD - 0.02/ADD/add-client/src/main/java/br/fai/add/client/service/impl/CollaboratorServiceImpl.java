package br.fai.add.client.service.impl;

import br.fai.add.client.service.CollaboratorService;
import br.fai.add.client.service.RestService;
import br.fai.add.model.entities.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CollaboratorServiceImpl implements CollaboratorService<Collaborator> {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api/";

    private String buildEndpoint(String resource) {
        return BASE_ENDPOINT + resource;
    }

    @Autowired
    private RestService<Collaborator> restService;

    @Override
    public int create(Collaborator entity) {

        return restService.post("collaborator/create", entity);
    }

    @Override
    public List<Collaborator> find() {

        return restService.get("collaborator/find");
    }

    @Override
    public List<Collaborator> findCollaboratorsByForm(int id) {
        return restService.getListById("collaborator/findCollaboratorsByForm/" + id);
    }

    @Override
    public List<Collaborator> findCollaboratorsByOrganization(int id) {
        return restService.getListById("collaborator/findCollaboratorsByOrganization/" + id);
    }


    @Override
    public Collaborator findById(int id) {

        return restService.getById("collaborator/find/" + id, Collaborator.class);
    }

    @Override
    public boolean deleteById(int id) {

        return restService.deleteById("collaborator/delete/" + id);
    }

    @Override
    public Collaborator validateUsernameAndPassword(String username, String password) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<String> httpEntity = new HttpEntity<>("");

            String resource = "account/login?username=" + username + "&password=" + password;


            ResponseEntity<Collaborator> responseEntity = restTemplate.exchange(buildEndpoint(resource), HttpMethod.POST, httpEntity, Collaborator.class);

            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                return null;
            }

            Collaborator collaborator = responseEntity.getBody();

            return collaborator;
        } catch (RestClientException e) {
            e.printStackTrace();
            return null;
        }
    }
}
