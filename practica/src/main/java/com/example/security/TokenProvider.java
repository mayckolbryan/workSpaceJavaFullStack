package com.example.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;


@Component
@Slf4j
public class TokenProvider implements InitializingBean {

    @Value("${security.base64-key}")
    private String base64Secret;

    @Value("${security.key}")
    private String secret;

    private static final String AUTHORITIES_KEY = "auth";

    private Key key;

    @Value("${security.time-token-validity}")
    private String tokenValidityInSeconds;

    @Value("${security.time-token-validity-remember}")
    private String tokenValidityInMSecondsForRememberMe;

    private Long tokenValidityInMilliseconds;

    private Long tokenValidityInMillisecondsForRememberMe;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes;
        //String secret = jHipsterProperties.getSecurity().getAuthentication().getJwt().getSecret();
        if (!StringUtils.isEmpty(this.secret)) {
            log.warn("Warning: the JWT key used is not Base64-encoded. " +
                    "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security.");
            keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        } else {
            log.debug("Using a Base64-encoded JWT secret key");
            //keyBytes = Decoders.BASE64.decode(jHipsterProperties.getSecurity().getAuthentication().getJwt().getBase64Secret());
            keyBytes = Decoders.BASE64.decode(base64Secret);
        }
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds =
                1000 * Long.parseLong(tokenValidityInSeconds);
        this.tokenValidityInMillisecondsForRememberMe =
                1000 * Long.parseLong(tokenValidityInMSecondsForRememberMe);
    }

    public String createToken(Authentication authentication, boolean rememberMe) {
        String authorities =authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now =(new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }
}
