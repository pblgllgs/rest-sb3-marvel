package com.pblgllgs.restsb3marvel.service;

import com.pblgllgs.restsb3marvel.dto.MyPageable;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.dto.CharacterDto;

import java.util.List;

public interface CharacterService {
    List<CharacterDto> findAllCharacters(MyPageable myPageable, String name, int[] comics, int[] series);

    CharacterDto.CharacterInfoDto findInfoById(Long characterId);
}
