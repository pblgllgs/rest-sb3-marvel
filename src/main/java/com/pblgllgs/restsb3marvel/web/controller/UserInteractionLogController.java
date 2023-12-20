package com.pblgllgs.restsb3marvel.web.controller;
/*
 *
 * @author pblgl
 * Created on 20-12-2023
 *
 */

import com.pblgllgs.restsb3marvel.dto.GetUserInteractionLogDto;
import com.pblgllgs.restsb3marvel.service.UserInteractionLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-interactions")
public class UserInteractionLogController {

    private final UserInteractionLogService userInteractionLogService;

    public UserInteractionLogController(UserInteractionLogService userInteractionLogService) {
        this.userInteractionLogService = userInteractionLogService;
    }

    @GetMapping
    public ResponseEntity<Page<GetUserInteractionLogDto>> findAllUserInteractionsLogs(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Pageable pageable = buildPageable(offset, limit);
        return ResponseEntity.ok(userInteractionLogService.findAll(pageable));
    }

    private static Pageable buildPageable(int offset, int limit) {
        Pageable pageable = null;
        if (offset < 0) throw new IllegalArgumentException("El atributo offset no puede ser menor a 0");
        if (limit <= 0) throw new IllegalArgumentException("El atributo offset no puede ser menor o igual a 0");
        if (offset == 0) pageable = PageRequest.of(0, limit);
        else pageable = PageRequest.of(offset / limit, limit);
        return pageable;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Page<GetUserInteractionLogDto>> findByUsername(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit,
            @PathVariable("username") String username
    ){
        Pageable pageable = buildPageable(offset, limit);
        return ResponseEntity.ok(userInteractionLogService.findById(pageable, username));
    }
}
