package com.pblgllgs.restsb3marvel.web.controller;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

import com.pblgllgs.restsb3marvel.dto.MyPageable;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.dto.CharacterDto;
import com.pblgllgs.restsb3marvel.service.impl.CharacterServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterServiceImpl characterService;

    public CharacterController(CharacterServiceImpl characterService) {
        this.characterService = characterService;
    }

    @PreAuthorize("hasAuthority('character:read-all')")
    @GetMapping
    public ResponseEntity<List<CharacterDto>> findAllCharacters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int[] comics,
            @RequestParam(required = false) int[] series,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "10") long limit

    ) {
        MyPageable myPageable = new MyPageable(offset, limit);
        return ResponseEntity.ok(characterService.findAllCharacters(myPageable, name, comics, series));
    }

    @PreAuthorize("hasAuthority('character:read-detail')")
    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterDto.CharacterInfoDto> findInfoById(
            @PathVariable("characterId") Long characterId
    ) {
        return ResponseEntity.ok(characterService.findInfoById(characterId));
    }
}
