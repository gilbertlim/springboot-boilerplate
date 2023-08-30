package com.megazone.springbootboilerplate.coupon.domain;

public abstract class DiscountPolicyChain {

    protected DiscountPolicyChain next;

    public final void next(DiscountPolicyChain nextDiscountPolicyChain) {
        this.next = nextDiscountPolicyChain;
    }

    public final int apply(int money) {
        if (!isSupported(money) && !hasNext()) {
            return money;
        }

        if (!isSupported(money) && hasNext()) {
            return next.apply(money);
        }

        int result = discount(money);
        if (hasNext()) {
            return next.apply(result);
        }
        return result;
    }

    private boolean hasNext() {
        return next != null;
    }

    public abstract int discount(int money);

    public abstract boolean isSupported(int money);
}
