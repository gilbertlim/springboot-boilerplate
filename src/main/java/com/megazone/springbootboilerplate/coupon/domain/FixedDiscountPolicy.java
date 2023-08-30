package com.megazone.springbootboilerplate.coupon.domain;

public class FixedDiscountPolicy extends DiscountPolicyChain {

    @Override
    public int discount(int money) {
        return money - 100;
    }

    @Override
    public boolean isSupported(int money) {
        return true;
    }
}
