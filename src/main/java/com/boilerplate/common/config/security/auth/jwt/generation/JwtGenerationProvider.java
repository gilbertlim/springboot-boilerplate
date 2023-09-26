package com.boilerplate.common.config.security.auth.jwt.generation;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.boilerplate.common.config.security.auth.data.dto.TokenPair;
import com.boilerplate.common.config.security.auth.jwt.JwtProcessor;
import com.boilerplate.common.config.security.auth.jwt.authentication.JwtAuthenticationToken;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtGenerationProvider implements AuthenticationProvider {

    private final JwtProcessor jwtProcessor;
    private final UserDetailsService userDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = userDetailService.loadUserByUsername(id);
        if (!matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("로그인 정보가 올바르지 않습니다.");
        }

        TokenPair tokenPair = jwtProcessor.createToken(id, userDetails.getAuthorities());
        return JwtAuthenticationToken.authenticatedToken(id, tokenPair, userDetails.getAuthorities());
    }

    private boolean matches(String rawPassword, String encryptedPassword) {
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
