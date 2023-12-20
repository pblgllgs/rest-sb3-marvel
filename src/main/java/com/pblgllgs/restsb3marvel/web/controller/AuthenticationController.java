package com.pblgllgs.restsb3marvel.web.controller;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

import com.pblgllgs.restsb3marvel.dto.security.LoginRequest;
import com.pblgllgs.restsb3marvel.dto.security.LoginResponse;
import com.pblgllgs.restsb3marvel.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    @PreAuthorize("permitAll")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok(authenticationService.authenticate(loginRequest));
    }

    @PostMapping("/logout")
    @PreAuthorize("permitAll")
    public void logout() {
        authenticationService.logout();
    }
}
