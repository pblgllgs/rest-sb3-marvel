package com.pblgllgs.restsb3marvel.web.interceptor;

import com.pblgllgs.restsb3marvel.exception.ApiErrorException;
import com.pblgllgs.restsb3marvel.persistence.entity.UserInteractionLog;
import com.pblgllgs.restsb3marvel.persistence.repository.UserInteractionLogRepository;
import com.pblgllgs.restsb3marvel.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

/*
 *
 * @author pblgl
 * Created on 20-12-2023
 *
 */
@Component
public class UserInteractionInterceptor implements HandlerInterceptor {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserInteractionInterceptor.class);

    @Autowired
    private UserInteractionLogRepository userInteractionLogRepository;

    @Autowired
    @Lazy
    private AuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("Interceptor: {}", this.getClass().getName());
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/comics") || requestURI.startsWith("/characters") ){
            UserInteractionLog log = new UserInteractionLog();
            log.setHttpMethod(request.getMethod());
            log.setUrl(request.getRequestURL().toString());
            UserDetails user = authenticationService.getUserloggedIn();
            log.setUsername(user.getUsername());
            log.setRemoteAddress(request.getRemoteAddr());
            log.setTimestamp(LocalDateTime.now());
            try{
                userInteractionLogRepository.save(log);
                return true;
            }catch (Exception ex){
                throw new ApiErrorException("No se logro guardar el registro de la interacion correctamente");
            }
        }
        return true;
    }

}
