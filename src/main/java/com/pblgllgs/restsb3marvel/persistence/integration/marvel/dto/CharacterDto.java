package com.pblgllgs.restsb3marvel.persistence.integration.marvel.dto;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

public record CharacterDto(
        Long id,
        String name,
        String description,
        String modified,
        String resourceURI) {

    public record CharacterInfoDto(
            String imagePath,
            String description) {


    }
}
