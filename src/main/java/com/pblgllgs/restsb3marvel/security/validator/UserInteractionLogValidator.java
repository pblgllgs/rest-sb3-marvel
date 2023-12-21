package com.pblgllgs.restsb3marvel.security.validator;
/*
 *
 * @author pblgl
 * Created on 20-12-2023
 *
 */

import com.pblgllgs.restsb3marvel.service.AuthenticationService;
import org.springframework.stereotype.Component;

@Component("interactionLogValidator")
public class UserInteractionLogValidator {

    private final AuthenticationService authenticationService;

    public UserInteractionLogValidator(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public boolean validate(String username){
        String userLoggedIn =  authenticationService.getUserloggedIn().getUsername();
        return userLoggedIn.equals(username);
    }
}
