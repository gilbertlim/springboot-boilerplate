package com.boilerplate.shop.domain.data.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class ShopWithReviewsQuery {

    private Long id;
    private String name;
    private List<ReviewQuery> reviews;
}
