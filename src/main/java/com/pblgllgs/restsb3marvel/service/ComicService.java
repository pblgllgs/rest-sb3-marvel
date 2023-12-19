package com.pblgllgs.restsb3marvel.service;

import com.pblgllgs.restsb3marvel.dto.MyPageable;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.dto.ComicDto;

import java.util.List;

public interface ComicService {
    List<ComicDto> findAllComics(MyPageable myPageable, Long characterId);

    ComicDto findComicById(Long comicId);
}
