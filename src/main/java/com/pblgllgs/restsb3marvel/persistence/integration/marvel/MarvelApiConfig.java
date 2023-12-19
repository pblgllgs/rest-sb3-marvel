package com.pblgllgs.restsb3marvel.persistence.integration.marvel;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class MarvelApiConfig {

    @Qualifier("md5Encoder")
    private final PasswordEncoder passwordEncoder;
    private final long timestamp = new Date(System.currentTimeMillis()).getTime();

    @Value("${integration.marvel.public-key}")
    private String publicKey;
    @Value("${integration.marvel.private-key}")
    private String privateKey;

    public MarvelApiConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private String getHash() {
        String hashDecoded = Long.toString(timestamp).concat(privateKey).concat(publicKey);
        return passwordEncoder.encode(hashDecoded);
    }

    public Map<String, String> getAuthenticationQueryParams() {
        return Map.of(
                "ts", Long.toString(timestamp),
                "apikey", publicKey,
                "hash", getHash()
        );
    }
}
