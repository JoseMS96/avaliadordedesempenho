package br.fai.add.client.service.impl;

import br.fai.add.client.service.RestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestServiceImpl<T> implements RestService<T> {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api/";

    private String buildEndpoint(String resource) {
        return BASE_ENDPOINT + resource;
    }

    @Override
    public HttpHeaders getAuthenticatedHeaders(String username, String password) {
        return null;
    }

    @Override
    public HttpHeaders getRequestHeaders() {
        return null;
    }

    @Override
    public List<T> get(String resource) {

        List<T> response = null;

        final RestTemplate restTemplate = new RestTemplate();
        try {
            final HttpEntity<String> requestEntity = new HttpEntity<>("");

            ResponseEntity<List<T>> requestResponse = restTemplate.exchange(buildEndpoint(resource), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<T>>() {
            });
            //para retornar objeto generics

            response = requestResponse.getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public List<T> getListById(String resource) {

        List<T> response = null;

        final RestTemplate restTemplate = new RestTemplate();
        try {
            final HttpEntity<String> requestEntity = new HttpEntity<>("");

            ResponseEntity<List<T>> requestResponse = restTemplate.exchange(buildEndpoint(resource), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<T>>() {
            });
            //para retornar objeto generics

            response = requestResponse.getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public T getById(String resource, Class<T> clazz) {

        T response = null;

        final RestTemplate restTemplate = new RestTemplate();

        try {
            final HttpEntity<String> requestEntity = new HttpEntity<>("");

            //JSON para não precisar de cast
            final ResponseEntity<String> requestResponse = restTemplate.exchange(buildEndpoint(resource), HttpMethod.GET, requestEntity, String.class);

            final Gson gson = new GsonBuilder().setDateFormat("YY-MM-DD").create();

            response = gson.fromJson(requestResponse.getBody(), clazz);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public int post(String resource, T entity) {


        final RestTemplate restTemplate = new RestTemplate();

        try {
            final HttpEntity<T> httpEntity = new HttpEntity<>(entity);

            final ResponseEntity<String> responseEntity = restTemplate.exchange(buildEndpoint(resource), HttpMethod.POST, httpEntity, String.class);

            final String response = responseEntity.getBody();
            return Integer.parseInt(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean put(String resource, T entity) {

        boolean response = false;

        final RestTemplate restTemplate = new RestTemplate();

        try {
            final HttpEntity<T> httpEntity = new HttpEntity<>(entity);

            final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(buildEndpoint(resource), HttpMethod.PUT, httpEntity, Boolean.class);

            response = responseEntity.getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return response;
    }

    @Override
    public boolean deleteById(String resource) {

        boolean response = false;

        final RestTemplate restTemplate = new RestTemplate();

        try {
            HttpEntity<String> httpEntity = new HttpEntity<>("");

            final ResponseEntity<Boolean> requestResponse = restTemplate.exchange(buildEndpoint(resource), HttpMethod.DELETE, httpEntity, Boolean.class);

            response = requestResponse.getBody();

        } catch (RestClientException e) {
            e.printStackTrace();
        }

        return response;
    }


}
