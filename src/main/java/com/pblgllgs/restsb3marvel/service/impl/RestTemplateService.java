package com.pblgllgs.restsb3marvel.service.impl;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

import com.pblgllgs.restsb3marvel.exception.ApiErrorException;
import com.pblgllgs.restsb3marvel.service.HttpClientService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class RestTemplateService implements HttpClientService {

    private final RestTemplate restTemplate;

    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T doGet(String endpoint, Map<String, String> queryParams, Class<T> responseType) {
        String finalUrl = getFinalUrl(endpoint, queryParams);
        HttpEntity httpEntity = new HttpEntity(getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.GET, httpEntity, responseType);
        if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            String message = String
                    .format(
                            "Error al consumir endpoint [ {} - {} ], codigo de respuesta es: {}",
                            HttpMethod.GET,
                            endpoint,
                            response.getStatusCode()
                    );
            throw new ApiErrorException(message);
        }
        return response.getBody();
    }

    @Override
    public <T, R> T doPost(String endpoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        String finalUrl = getFinalUrl(endpoint, queryParams);
        HttpEntity httpEntity = new HttpEntity(bodyRequest, getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.POST, httpEntity, responseType);
        if (
                response.getStatusCode().value() != HttpStatus.OK.value() ||
                        response.getStatusCode().value() != HttpStatus.CREATED.value()
        ) {
            String message = String
                    .format(
                            "Error al consumir endpoint [ {} - {} ], codigo de respuesta es: {}",
                            HttpMethod.POST,
                            endpoint,
                            response.getStatusCode()
                    );
            throw new ApiErrorException(message);
        }
        return response.getBody();
    }

    @Override
    public <T, R> T doPut(String endpoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        String finalUrl = getFinalUrl(endpoint, queryParams);
        HttpEntity httpEntity = new HttpEntity(bodyRequest, getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.PUT, httpEntity, responseType);
        if (
                response.getStatusCode().value() != HttpStatus.OK.value() ||
                        response.getStatusCode().value() != HttpStatus.CREATED.value()
        ) {
            String message = String
                    .format(
                            "Error al consumir endpoint [ {} - {} ], codigo de respuesta es: {}",
                            HttpMethod.PUT,
                            endpoint,
                            response.getStatusCode()
                    );
            throw new ApiErrorException(message);
        }
        return response.getBody();
    }

    @Override
    public <T> T doDelete(String endpoint, Map<String, String> queryParams, Class<T> responseType) {
        String finalUrl = getFinalUrl(endpoint, queryParams);
        HttpEntity httpEntity = new HttpEntity(getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.DELETE, httpEntity, responseType);
        if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            String message = String
                    .format(
                            "Error al consumir endpoint [ {} - {} ], codigo de respuesta es: {}",
                            HttpMethod.DELETE,
                            endpoint,
                            response.getStatusCode()
                    );
            throw new ApiErrorException(message);
        }
        return response.getBody();
    }

    private static String getFinalUrl(String endpoint, Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint);
        if (queryParams != null) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        String finalUrl = builder.build().toUriString();
        return finalUrl;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
