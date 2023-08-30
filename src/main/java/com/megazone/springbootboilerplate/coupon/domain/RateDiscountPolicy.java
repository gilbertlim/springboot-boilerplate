package com.megazone.springbootboilerplate.coupon.domain;

public class RateDiscountPolicy extends DiscountPolicyChain {

    @Override
    public int discount(int money) {
        int discountAmount = (int) (money * 0.1);
        return money - discountAmount;
    }

    @Override
    public boolean isSupported(int money) {
        return money >= 1000;
    }
}
