package com.boilerplate.jasypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@TestPropertySource(properties = {"jasypt.encryptor.password=1234"})
@SpringBootTest
class JasyptTest {

    @Autowired
    StringEncryptor stringEncryptor;

    @DisplayName("Jasypt 암호화 출력")
    @Test
    void encryptTest() {
        String encrypted = stringEncryptor.encrypt("1234");
        System.out.println("encrypted: " + encrypted);
    }
}
