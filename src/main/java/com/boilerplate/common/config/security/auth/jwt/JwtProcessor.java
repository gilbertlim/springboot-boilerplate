package com.boilerplate.common.config.security.auth.jwt;

import java.time.Duration;
import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.boilerplate.common.config.security.auth.data.dto.TokenPair;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtProcessor {

    public static final String ACCESS_TOKEN = "access-token";
    public static final String REFRESH_TOKEN = "refresh-token";

    private final JwtProperties jwtProperties;

    public TokenPair createToken(String id, Collection<? extends GrantedAuthority> authorities) {
        String accessToken = createToken(ACCESS_TOKEN, id, authorities, jwtProperties.access().expirationTime());
        String refreshToken = createToken(REFRESH_TOKEN, "", Collections.emptyList(), jwtProperties.refresh().expirationTime());

        return new TokenPair(accessToken, refreshToken);
    }

    public String createToken(String subject, String id, Collection<? extends GrantedAuthority> authorities, Duration duration) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + duration.toMillis());

        return JWT.create()
            .withSubject(subject)
            .withIssuedAt(now)
            .withExpiresAt(expiredDate)
            .withClaim("id", id)
            .withClaim("role", authorities.stream().map(GrantedAuthority::getAuthority).toList())
            .sign(Algorithm.HMAC512(jwtProperties.secretKey()));
    }

    public String getId(String token) {
        return JWT.decode(token)
            .getClaim("id")
            .asString();
    }

    public List<? extends GrantedAuthority> getAuthorities(String token) {
        List<String> roles = JWT.decode(token).getClaim("role").asList(String.class);
        return AuthorityUtils.createAuthorityList(roles);
    }

    public boolean isValidToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(jwtProperties.secretKey()))
                .build()
                .verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }

        return true;
    }
}
