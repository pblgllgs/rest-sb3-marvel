package com.pblgllgs.restsb3marvel.service.impl;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

import com.pblgllgs.restsb3marvel.dto.MyPageable;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.dto.CharacterDto;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.repository.CharacterRepository;
import com.pblgllgs.restsb3marvel.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public List<CharacterDto> findAllCharacters(MyPageable myPageable, String name, int[] comics, int[] series) {
        return characterRepository.findAllCharacters(myPageable, name, comics, series);
    }

    @Override
    public CharacterDto.CharacterInfoDto findInfoById(Long characterId) {
        return characterRepository.findInfoById(characterId);
    }
}
