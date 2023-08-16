CREATE TABLE shop
(
    shop_id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    shop_name VARCHAR(255) NOT NULL,
    shop_address VARCHAR(255) NOT NULL,
    shop_detail_address VARCHAR(255) NOT NULL,
    shop_tier VARCHAR(255) NOT NULL,
    shop_tier_code VARCHAR(255) NOT NULL
);
