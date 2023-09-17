-- Data
CREATE TABLE shop (
    shop_id             INT PRIMARY KEY AUTO_INCREMENT,
    shop_name           VARCHAR(255) NOT NULL,
    shop_address        VARCHAR(255) NOT NULL,
    shop_detail_address VARCHAR(255) NOT NULL,
    shop_tier           VARCHAR(255) NOT NULL,
    shop_tier_code      VARCHAR(255) NOT NULL
) DEFAULT CHARSET=utf8mb4;

CREATE TABLE review (
    review_id         INT PRIMARY KEY AUTO_INCREMENT,
    review_contents   VARCHAR(255) NOT NULL,
    shop_id           INT,
    FOREIGN KEY (shop_id) REFERENCES shop(shop_id)
) DEFAULT CHARSET=utf8mb4;

CREATE TABLE event_outbox_shop (
    event_id          INT PRIMARY KEY AUTO_INCREMENT,
    domain_id         INT NOT NULL,
    event_type        VARCHAR(50),
    published         CHAR(1) NOT NULL DEFAULT 'N',
    published_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

insert into shop (shop_name, shop_address, shop_detail_address, shop_tier, shop_tier_code)
values ('Shop A', '서울시 송파구 동남로 191', '101동 1101호', 'Bronze', 'B'),
       ('Shop B', '서울시 송파구 동남로 192', '201동 1102호', 'Bronze', 'B'),
       ('Shop C', '서울시 송파구 동남로 193', '301동 1103호', 'Bronze', 'B'),
       ('Shop D', '서울시 송파구 동남로 194', '401동 1104호', 'Bronze', 'B'),
       ('Shop E', '서울시 송파구 동남로 195', '501동 1105호', 'Bronze', 'B'),
       ('Shop F', '서울시 송파구 동남로 196', '601동 1106호', 'Bronze', 'B'),
       ('Shop G', '서울시 송파구 동남로 197', '701동 1107호', 'Bronze', 'B'),
       ('Shop H', '서울시 송파구 동남로 198', '801동 1108호', 'Bronze', 'B'),
       ('Shop I', '서울시 송파구 동남로 199', '901동 1109호', 'Bronze', 'B'),
       ('Shop J', '서울시 송파구 동남로 190', '101동 1110호', 'Bronze', 'B'),
       ('Shop K', '서울시 송파구 동남로 192', '202동 2202호', 'Silver', 'S'),
       ('Shop L', '서울시 송파구 동남로 193', '303동 3303호', 'Gold', 'G');

insert into review (review_contents, shop_id)
values ('좋아요', 1),
       ('싫어요', 1),
       ('나가주세요', 1);