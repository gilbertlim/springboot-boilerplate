CREATE TABLE shop
(
    shop_id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    shop_name VARCHAR(255) NOT NULL,
    shop_address VARCHAR(255) NOT NULL,
    shop_detail_address VARCHAR(255) NOT NULL,
    shop_tier VARCHAR(255) NOT NULL,
    shop_tier_code VARCHAR(255) NOT NULL
);

CREATE TABLE review
(
    review_id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    review_contents VARCHAR(255) NOT NULL,
    shop_id     BIGINT,
    FOREIGN KEY (shop_id) REFERENCES shop(shop_id)
);
