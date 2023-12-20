package com.pblgllgs.restsb3marvel.service;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

import com.pblgllgs.restsb3marvel.dto.security.LoginRequest;
import com.pblgllgs.restsb3marvel.dto.security.LoginResponse;
import com.pblgllgs.restsb3marvel.persistence.entity.User;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private final HttpSecurity httpSecurity;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(
            HttpSecurity httpSecurity,
            UserDetailsService userDetailsService,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.httpSecurity = httpSecurity;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {
        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.username());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, loginRequest.password(), user.getAuthorities()
        );
        authenticationManager.authenticate(authentication);
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        return new LoginResponse(jwt);
    }

    private Map<String, Object> generateExtraClaims(UserDetails user) {
        Map<String, Object> extraClaims = new HashMap<>();
        String roleName = ((User) user).getRole().getName().name();
        extraClaims.put("role", roleName);
        extraClaims.put("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return extraClaims;
    }

    public void logout() {
        try{
            httpSecurity
                    .logout(logout -> logout.deleteCookies("JSESSIONID")
                            .clearAuthentication(true)
                            .invalidateHttpSession(true));
        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    public UserDetails getUserloggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof UsernamePasswordAuthenticationToken)){
            throw new AuthenticationCredentialsNotFoundException("Se requiere  authenticacion completa");
        }
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        return (UserDetails) auth.getPrincipal();
    }
}
