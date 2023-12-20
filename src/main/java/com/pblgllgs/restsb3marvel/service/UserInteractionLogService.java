package com.pblgllgs.restsb3marvel.service;


import com.pblgllgs.restsb3marvel.dto.GetUserInteractionLogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserInteractionLogService {
    Page<GetUserInteractionLogDto> findAll(Pageable pageable);

    Page<GetUserInteractionLogDto> findById(Pageable pageable, String username);
}
