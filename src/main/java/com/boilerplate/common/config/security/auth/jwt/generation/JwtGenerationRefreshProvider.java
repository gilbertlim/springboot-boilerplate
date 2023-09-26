package com.boilerplate.common.config.security.auth.jwt.generation;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import com.boilerplate.common.config.security.auth.TokenRepository;
import com.boilerplate.common.config.security.auth.data.dto.TokenPair;
import com.boilerplate.common.config.security.auth.jwt.JwtProcessor;
import com.boilerplate.common.config.security.auth.jwt.authentication.JwtAuthenticationToken;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class JwtGenerationRefreshProvider implements AuthenticationProvider {

    private final JwtProcessor jwtProcessor;
    private final TokenRepository tokenRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
        String refreshToken = authenticationToken.getTokenPair().refreshToken();
        String preAccessToken = tokenRepository.findAccessTokenByRefreshToken(refreshToken)
            .orElseThrow(() -> new BadCredentialsException("갱신 정보가 올바르지 않습니다."));

        String id = jwtProcessor.getId(preAccessToken);
        var roles = jwtProcessor.getAuthorities(preAccessToken);
        TokenPair tokenPair = jwtProcessor.createToken(id, roles);
        return JwtAuthenticationToken.authenticatedToken(id, tokenPair, roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
