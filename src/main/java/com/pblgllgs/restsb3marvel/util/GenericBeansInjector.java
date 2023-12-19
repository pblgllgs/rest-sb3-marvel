package com.pblgllgs.restsb3marvel.util;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GenericBeansInjector {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
