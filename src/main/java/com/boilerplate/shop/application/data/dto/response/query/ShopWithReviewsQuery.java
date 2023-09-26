package com.boilerplate.shop.application.data.dto.response.query;

import java.util.List;

import lombok.Getter;

@Getter
public class ShopWithReviewsQuery {

    private Long id;
    private String name;
    private List<ReviewQuery> reviews;
}
