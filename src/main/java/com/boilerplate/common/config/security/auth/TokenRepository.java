package com.boilerplate.common.config.security.auth;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.boilerplate.common.config.security.auth.domain.data.dto.TokenPair;

@Component
public class TokenRepository {
    //TODO: redis 혹은 db로 구현

    private final Map<String, String> store = new ConcurrentHashMap<>();

    public void save(TokenPair tokenPair) {
        store.put(tokenPair.refreshToken(), tokenPair.accessToken());
    }

    public Optional<String> findAccessTokenByRefreshToken(String refreshToken) {
        return Optional.ofNullable(store.get(refreshToken));
    }
}
