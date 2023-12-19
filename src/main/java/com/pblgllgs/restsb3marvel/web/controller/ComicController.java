package com.pblgllgs.restsb3marvel.web.controller;

import com.pblgllgs.restsb3marvel.dto.MyPageable;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.dto.ComicDto;
import com.pblgllgs.restsb3marvel.service.ComicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */
@RestController
@RequestMapping("/comics")
public class ComicController {

    private final ComicService comicService;

    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping
    public ResponseEntity<List<ComicDto>> findAllComics(
            @RequestParam(required = false) Long characterId,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "10") long limit
    ){
        MyPageable myPageable = new MyPageable(offset, limit);
        return ResponseEntity.ok(comicService.findAllComics(myPageable,characterId));
    }

    @GetMapping("/{comicId}")
    public ResponseEntity<ComicDto> findComicById(
            @PathVariable("comicId") Long comicId
    ){
        return ResponseEntity.ok(comicService.findComicById(comicId));
    }
}
