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

CREATE TABLE shedlock
(
    name       VARCHAR(64),
    lock_until TIMESTAMP(3) NULL,
    locked_at  TIMESTAMP(3) NULL,
    locked_by  VARCHAR(255),
    PRIMARY KEY (name)
)
