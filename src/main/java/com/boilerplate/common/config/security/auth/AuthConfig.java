package com.boilerplate.common.config.security.auth;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.boilerplate.common.config.security.auth.exception.CustomAccessDeniedHandler;
import com.boilerplate.common.config.security.auth.exception.CustomAuthenticationEntryPoint;
import com.boilerplate.common.config.security.auth.jwt.authentication.JwtAuthenticationFilter;
import com.boilerplate.common.config.security.auth.jwt.authentication.JwtAuthenticationProvider;
import com.boilerplate.common.config.security.auth.jwt.generation.JwtGenerationFilter;
import com.boilerplate.common.config.security.auth.role.RoleType;
import com.boilerplate.member.domain.port.repository.MemberRepository;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class AuthConfig {

    private static final RequestMatcher ALLOWED_REQUEST_MATCHER;

    static {
        ALLOWED_REQUEST_MATCHER = RequestMatchers.anyOf(
            AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/docs/**"),
            AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/shops"),
            AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/shops/*"),
            AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/members")

        );
    }

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public WebSecurityCustomizer configure() {
        return web -> web.ignoring()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
            .requestMatchers("/static/**", "/error");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        AuthenticationManager authenticationManger,
        CustomAuthenticationEntryPoint entryPoint,
        CustomAccessDeniedHandler accessDeniedHandler,
        TokenRepository tokenRepository,
        ObjectMapper objectMapper,
        MemberRepository memberRepository
    ) throws Exception {
        var jwtGenerationFilter = new JwtGenerationFilter(authenticationManger, objectMapper, tokenRepository, memberRepository);
        var jwtAuthFilter = new JwtAuthenticationFilter(ALLOWED_REQUEST_MATCHER, jwtAuthenticationProvider);

        return http.csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(ALLOWED_REQUEST_MATCHER).permitAll()
                .requestMatchers(HttpMethod.GET, "/members/me").hasRole("MEMBER")
                .requestMatchers(HttpMethod.GET, "/members/admin").hasRole("ADMIN")
                .anyRequest().authenticated())
            .addFilterBefore(jwtGenerationFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(jwtAuthFilter, JwtGenerationFilter.class)
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(entryPoint)
                .accessDeniedHandler(accessDeniedHandler))
            .build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = Arrays.stream(RoleType.values()).map(RoleType::getName)
            .collect(Collectors.joining(" > "));
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, List<AuthenticationProvider> providers) throws Exception {
        var authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        providers.forEach(authenticationManagerBuilder::authenticationProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
