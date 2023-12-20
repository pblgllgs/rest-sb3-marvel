package com.pblgllgs.restsb3marvel.web.interceptor;
/*
 *
 * @author pblgl
 * Created on 20-12-2023
 *
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {

    private final UserInteractionInterceptor userInteractionInterceptor;

    public InterceptorsConfig(UserInteractionInterceptor userInteractionInterceptor) {
        this.userInteractionInterceptor = userInteractionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInteractionInterceptor);
    }
}
