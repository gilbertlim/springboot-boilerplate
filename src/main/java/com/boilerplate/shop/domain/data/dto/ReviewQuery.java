package com.boilerplate.shop.domain.data.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class ReviewQuery {

    private Long id;
    private String contents;

    public ReviewQuery(Long id, String contents) {
        this.id = id;
        this.contents = contents;
    }
}
