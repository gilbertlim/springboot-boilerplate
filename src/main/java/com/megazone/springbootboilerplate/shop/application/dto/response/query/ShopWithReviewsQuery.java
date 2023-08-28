package com.megazone.springbootboilerplate.shop.application.dto.response.query;

import lombok.Getter;

import java.util.List;

@Getter
public class ShopWithReviewsQuery {

    private Long id;
    private String name;
    private List<ReviewQuery> reviews;
}
