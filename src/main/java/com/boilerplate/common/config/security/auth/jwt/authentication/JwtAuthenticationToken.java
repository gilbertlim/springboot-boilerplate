package com.boilerplate.common.config.security.auth.jwt.authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import lombok.Getter;

import com.boilerplate.common.config.security.auth.domain.data.dto.TokenPair;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal;
    @Getter
    private final TokenPair tokenPair;

    public JwtAuthenticationToken(String principal, TokenPair tokenPair, List<? extends GrantedAuthority> grantedAuthorities, boolean authenticated) {
        super(grantedAuthorities);
        setAuthenticated(authenticated);
        this.principal = principal;
        this.tokenPair = tokenPair;
    }

    public static JwtAuthenticationToken authenticatedToken(String principal, String accessToken, List<? extends GrantedAuthority> grantedAuthorities) {
        return authenticatedToken(principal, new TokenPair(accessToken, ""), grantedAuthorities);
    }

    public static JwtAuthenticationToken authenticatedToken(String principal, TokenPair tokenPair, Collection<? extends GrantedAuthority> grantedAuthorities) {
        return authenticatedToken(principal, tokenPair, grantedAuthorities.stream().toList());
    }

    public static JwtAuthenticationToken authenticatedToken(String principal, TokenPair tokenPair, List<? extends GrantedAuthority> grantedAuthorities) {
        return new JwtAuthenticationToken(principal, tokenPair, grantedAuthorities, true);
    }

    public static JwtAuthenticationToken refreshToken(String principal, String refreshToken) {
        return new JwtAuthenticationToken(principal, new TokenPair("", refreshToken), null, true);
    }

    public static JwtAuthenticationToken unauthenticatedToken() {
        return new JwtAuthenticationToken(null, null, null, false);
    }

    @Override
    public Object getCredentials() {
        return tokenPair.accessToken();
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
