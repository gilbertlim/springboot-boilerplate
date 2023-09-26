package com.boilerplate.common.config.security.auth;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import com.boilerplate.common.config.security.auth.data.dto.TokenPair;

@Component
public class TokenRepository {

    private final Map<String, String> store = new ConcurrentHashMap<>();

    public void save(TokenPair tokenPair) {
        store.put(tokenPair.refreshToken(), tokenPair.accessToken());
    }

    public Optional<String> findAccessTokenByRefreshToken(String refreshToken) {
        return Optional.ofNullable(store.get(refreshToken));
    }
}
