package br.fai.add.client.service.impl;

import br.fai.add.client.service.OrganizationService;
import br.fai.add.client.service.RestService;
import br.fai.add.model.entities.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService<Organization> {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api/";

    private String buildEndpoint(String resource) {
        return BASE_ENDPOINT + resource;
    }

    @Autowired
    private RestService<Organization> restService;

    @Override
    public int create(Organization entity) {

        return restService.post("organization/create", entity);
    }

    @Override
    public List<Organization> find() {

        return restService.get("organization/find");
    }

    @Override
    public Organization findById(int id) {

        return restService.getById("organization/find/" + id, Organization.class);
    }

    @Override
    public boolean deleteById(int id) {

        return restService.deleteById("organization/delete/" + id);
    }

}
