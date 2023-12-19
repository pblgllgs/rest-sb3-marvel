package com.pblgllgs.restsb3marvel.persistence.integration.marvel.mapper;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.dto.ThumbnailDto;

public class ThumbnailMapper {

    public static ThumbnailDto toDto(JsonNode thumbnailNode){
        if (thumbnailNode == null) {
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }
        return new ThumbnailDto(
                thumbnailNode.get("path").asText(),
                thumbnailNode.get("extension").asText()
        );
    }
}
