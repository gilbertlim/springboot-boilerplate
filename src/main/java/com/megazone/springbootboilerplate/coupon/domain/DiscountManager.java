package com.megazone.springbootboilerplate.coupon.domain;

import java.util.Arrays;

public class DiscountManager {

    private final DiscountPolicyChain policyChain;

    public DiscountManager(DiscountPolicyChain... discountPolicyChains) {
        if (discountPolicyChains.length == 0) {
            throw new IllegalArgumentException();
        }

        Arrays.stream(discountPolicyChains)
            .reduce((base, next) -> {
                base.next(next);
                return next;
            });
        policyChain = discountPolicyChains[0];
    }

    public int calculate(int money) {
        return policyChain.apply(money);
    }
}
