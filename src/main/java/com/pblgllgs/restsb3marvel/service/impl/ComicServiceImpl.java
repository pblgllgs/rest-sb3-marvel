package com.pblgllgs.restsb3marvel.service.impl;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

import com.pblgllgs.restsb3marvel.dto.MyPageable;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.dto.ComicDto;
import com.pblgllgs.restsb3marvel.persistence.integration.marvel.repository.ComicRepository;
import com.pblgllgs.restsb3marvel.service.ComicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicServiceImpl implements ComicService {

    private final ComicRepository comicRepository;

    public ComicServiceImpl(ComicRepository comicRepository) {
        this.comicRepository = comicRepository;
    }

    @Override
    public List<ComicDto> findAllComics(MyPageable myPageable, Long characterId) {
        return comicRepository.findAllComics(myPageable, characterId);
    }

    @Override
    public ComicDto findComicById(Long comicId) {
        return comicRepository.findComicById(comicId);
    }
}
