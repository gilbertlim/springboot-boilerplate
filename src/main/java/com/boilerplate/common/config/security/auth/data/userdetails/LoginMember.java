package com.boilerplate.common.config.security.auth.data.userdetails;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.boilerplate.common.config.security.auth.role.RoleType;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class LoginMember implements UserDetails {

    private final String id;
    private final String password;
    private final List<? extends GrantedAuthority> authorities;

    public static LoginMember of(String id, String password, RoleType... roleTypes) {
        List<SimpleGrantedAuthority> roles = Arrays.stream(roleTypes)
            .map(RoleType::getName)
            .map(SimpleGrantedAuthority::new)
            .toList();
        return new LoginMember(id, password, roles);
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
