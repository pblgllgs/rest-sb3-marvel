package com.pblgllgs.restsb3marvel.mapper;
/*
 *
 * @author pblgl
 * Created on 20-12-2023
 *
 */

import com.pblgllgs.restsb3marvel.dto.GetUserInteractionLogDto;
import com.pblgllgs.restsb3marvel.persistence.entity.UserInteractionLog;

public class UserInteractionLogMapper {

    public static GetUserInteractionLogDto toDto(UserInteractionLog userInteractionLog){
        if(userInteractionLog == null) return null;

        return new GetUserInteractionLogDto(
                userInteractionLog.getId(),
                userInteractionLog.getUrl(),
                userInteractionLog.getHttpMethod(),
                userInteractionLog.getUsername(),
                userInteractionLog.getTimestamp(),
                userInteractionLog.getRemoteAddress()
        );
    }
}
