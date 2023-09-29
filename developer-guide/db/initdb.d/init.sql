-- Data
create table shop (
    shop_id             int primary key auto_increment,
    shop_name           varchar(255) not null,
    shop_address        varchar(255) not null,
    shop_detail_address varchar(255) not null,
    shop_tier           varchar(255) not null,
    shop_tier_code      varchar(255) not null
) default charset = utf8mb4;

create table review (
    review_id       int primary key auto_increment,
    review_contents varchar(255) not null,
    shop_id         int,
    foreign key (shop_id) references shop (shop_id)
) default charset = utf8mb4;

create table event_outbox_shop (
    event_id     int primary key auto_increment,
    domain_id    int     not null,
    event_type   varchar(50),
    published    char(1) not null default 'N',
    published_at timestamp        default CURRENT_TIMESTAMP
);

create table member (
    member_code     int primary key auto_increment,
    member_id       varchar(50),
    member_pw       varchar(500),
    member_nm       varchar(100),
    member_roles    varchar(100),
    last_lgn_dttm   datetime,
    member_reg_dttm timestamp default CURRENT_TIMESTAMP
);

insert into member (member_id, member_pw, member_nm, member_roles, last_lgn_dttm)
values ('gilbert@gmail.com', '{bcrypt}$2a$10$1oe1gAvqpGJZhtiZZJl2POekuUtXm2YBWiTuA1YDqf0MJXOPH.xwm', 'gilbert', 'ADMIN', null);


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

