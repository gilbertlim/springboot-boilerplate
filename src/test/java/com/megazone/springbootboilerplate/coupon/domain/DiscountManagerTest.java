package com.megazone.springbootboilerplate.coupon.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountManagerTest {

    @DisplayName("할인 순서가 적용된 계산")
    @CsvSource(value = {"1_000,900", "1_100,900"})
    @ParameterizedTest(name = "금액: {0}, 할인 적용된 금액: {1}")
    void discountTest(int input, int expected) {
        DiscountManager discountManager = new DiscountManager(new FixedDiscountPolicy(), new RateDiscountPolicy());

        int result = discountManager.calculate(input);

        Assertions.assertThat(result).isEqualTo(expected);
    }
}
