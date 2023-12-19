package com.pblgllgs.restsb3marvel.persistence.integration.marvel.repository;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.pblgllgs.restsb3marvel.dto.MyPageable;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.MarvelApiConfig;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.dto.ComicDto;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.mapper.ComicMapper;
import com.pblgllgs.restsb3marvel.service.HttpClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ComicRepository {

    private final MarvelApiConfig marvelApiConfig;
    private final HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;

    private String comicPath;

    @PostConstruct
    private void setPath() {
        comicPath = basePath.concat("/").concat("comics");
    }

    public ComicRepository(MarvelApiConfig marvelApiConfig, HttpClientService httpClientService) {
        this.marvelApiConfig = marvelApiConfig;
        this.httpClientService = httpClientService;
    }

    public List<ComicDto> findAllComics(MyPageable myPageable, Long characterId) {
        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(myPageable, characterId);
        JsonNode response = httpClientService.doGet(comicPath, marvelQueryParams, JsonNode.class);
        return ComicMapper.toDtoList(response);
    }

    private Map<String, String> getQueryParamsForFindAll(MyPageable myPageable, Long characterId) {
        Map<String, String> marvelQueryParams = marvelApiConfig.getAuthenticationQueryParams();
        marvelQueryParams.put("offset", Long.toString(myPageable.offset()));
        marvelQueryParams.put("limit", Long.toString(myPageable.limit()));
        if (characterId != null && characterId > 0) {
            marvelQueryParams.put("characters", Long.toString(characterId));
        }
        return marvelQueryParams;
    }

    public ComicDto findComicById(Long comicId) {
        Map<String, String> marvelQueryParams = marvelApiConfig.getAuthenticationQueryParams();
        String finalUrl = comicPath.concat("/").concat(Long.toString(comicId));
        JsonNode response = httpClientService.doGet(finalUrl,marvelQueryParams,JsonNode.class);
        return ComicMapper.toDtoList(response).get(0);
    }
}
