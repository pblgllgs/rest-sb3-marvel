package com.pblgllgs.restsb3marvel.service.impl;
/*
 *
 * @author pblgl
 * Created on 20-12-2023
 *
 */

import com.pblgllgs.restsb3marvel.dto.GetUserInteractionLogDto;
import com.pblgllgs.restsb3marvel.mapper.UserInteractionLogMapper;
import com.pblgllgs.restsb3marvel.persistence.repository.UserInteractionLogRepository;
import com.pblgllgs.restsb3marvel.service.UserInteractionLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserInteractionLogServiceImpl implements UserInteractionLogService {

    private final UserInteractionLogRepository userInteractionLogRepository;

    public UserInteractionLogServiceImpl(UserInteractionLogRepository userInteractionLogRepository) {
        this.userInteractionLogRepository = userInteractionLogRepository;
    }

    @Override
    public Page<GetUserInteractionLogDto> findAll(Pageable pageable) {
        return userInteractionLogRepository.findAll(pageable)
                .map(UserInteractionLogMapper::toDto);
    }

    @Override
    public Page<GetUserInteractionLogDto> findById(Pageable pageable, String username) {
        return userInteractionLogRepository.findByUsername(pageable,username)
                .map(UserInteractionLogMapper::toDto);
    }
}
