package com.pblgllgs.restsb3marvel.dto;
/*
 *
 * @author pblgl
 * Created on 20-12-2023
 *
 */

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record GetUserInteractionLogDto(
        Long id,
        String url,
        String httpMethod,
        String username,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        LocalDateTime timestamp,
        String remoteAddress
){
}
