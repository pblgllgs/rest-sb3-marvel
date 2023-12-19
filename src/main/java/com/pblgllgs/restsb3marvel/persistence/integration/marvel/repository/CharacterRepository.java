package com.pblgllgs.restsb3marvel.persistence.integration.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.pblgllgs.restsb3marvel.dto.MyPageable;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.MarvelApiConfig;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.dto.CharacterDto;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.mapper.CharacterMapper;
import com.pblgllgs.restsb3marvel.service.HttpClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Service
public class CharacterRepository {

    private final MarvelApiConfig marvelApiConfig;
    private final HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;

    private String characterPath;

    @PostConstruct
    private void setPath(){
        characterPath = basePath.concat("/").concat("characters");
    }

    public CharacterRepository(MarvelApiConfig marvelApiConfig, HttpClientService httpClientService) {
        this.marvelApiConfig = marvelApiConfig;
        this.httpClientService = httpClientService;
    }

    public List<CharacterDto> findAllCharacters(MyPageable myPageable, String name, int[] comics, int[] series) {
        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(myPageable, name, comics, series);
        JsonNode response = httpClientService.doGet(characterPath, marvelQueryParams, JsonNode.class);
        return CharacterMapper.toDtoList(response);
    }

    private Map<String, String> getQueryParamsForFindAll(MyPageable myPageable, String name, int[] comics, int[] series) {
        Map<String, String> marvelQueryParams = marvelApiConfig.getAuthenticationQueryParams();
        marvelQueryParams.put("offset", Long.toString(myPageable.offset()));
        marvelQueryParams.put("limit", Long.toString(myPageable.limit()));
        if (StringUtils.hasText(name)) {
            marvelQueryParams.put("name", name);
        }

        if (comics != null) {
            String comicsAsString = joinInArray(comics);
            marvelQueryParams.put("name", comicsAsString);
        }

        if (series != null) {
            String seriesAsString = joinInArray(series);
            marvelQueryParams.put("series", seriesAsString);
        }

        return marvelQueryParams;
    }

    private String joinInArray(int[] comics) {
        List<String> stringArray = IntStream.of(comics).boxed().map(Object::toString).toList();
        return String.join(",", stringArray);
    }

    public CharacterDto.CharacterInfoDto findInfoById(Long characterId) {
        Map<String, String> marvelQueryParams = marvelApiConfig.getAuthenticationQueryParams();
        String finalUrl = characterPath.concat("/").concat(Long.toString(characterId));
        JsonNode response = httpClientService.doGet(finalUrl, marvelQueryParams,JsonNode.class);
        return CharacterMapper.toInfoDtoList(response).get(0);
    }
}
