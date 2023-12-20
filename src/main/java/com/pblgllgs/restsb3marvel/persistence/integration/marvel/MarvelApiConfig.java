package com.pblgllgs.restsb3marvel.persistence.integration.marvel;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MarvelApiConfig {

    @Qualifier("md5Encoder")
    @Autowired
    private PasswordEncoder passwordEncoder;
    private long timestamp = new Date(System.currentTimeMillis()).getTime();

    @Value("${integration.marvel.public-key}")
    private String publicKey;
    @Value("${integration.marvel.private-key}")
    private String privateKey;

    private String getHash() {
        String hashDecoded = Long.toString(timestamp).concat(privateKey).concat(publicKey);
        return passwordEncoder.encode(hashDecoded);
    }

    public Map<String, String> getAuthenticationQueryParams() {
        Map<String, String> securityQueryParams = new HashMap<>();

        securityQueryParams.put("ts", Long.toString(timestamp));
        securityQueryParams.put("apikey", publicKey);
        securityQueryParams.put("hash", this.getHash());

        return securityQueryParams;
    }
}
