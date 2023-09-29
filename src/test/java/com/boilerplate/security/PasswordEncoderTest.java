package com.boilerplate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource(properties = {"jwt.secret-key=test1234"})
@SpringBootTest
public class PasswordEncoderTest {

    @Autowired PasswordEncoder passwordEncoder;

    @DisplayName("Password 암호화 출력")
    @Test
    void encryptTest() {
        String encode = passwordEncoder.encode("123456");
        System.out.println("encoded: " + encode);
    }

    @DisplayName("암호화값 비교")
    @Test
    void passwordValidationTest() {
        String encodedPassword = passwordEncoder.encode("123456");
        assertTrue(passwordEncoder.matches("123456", encodedPassword));
        assertTrue(passwordEncoder.matches("123456", "{bcrypt}$2a$10$1oe1gAvqpGJZhtiZZJl2POekuUtXm2YBWiTuA1YDqf0MJXOPH.xwm"));
    }

}
