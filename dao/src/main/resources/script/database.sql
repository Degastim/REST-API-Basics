CREATE TABLE gift_certificates
(
    gift_certificate_id   bigint         NOT NULL AUTO_INCREMENT,
    gift_certificate_name varchar(40)    NOT NULL,
    description           text           NOT NULL,
    price                 decimal(10, 2) NOT NULL,
    duration              int            NOT NULL,
    create_date           datetime       NOT NULL,
    last_update_date      datetime       NOT NULL,
    operation             VARCHAR(10)    NOT NULL,
    PRIMARY KEY (gift_certificate_id)
);

CREATE TABLE tags
(
    tag_id           bigint       NOT NULL AUTO_INCREMENT,
    tag_name         varchar(256) NOT NULL,
    create_date      datetime     NOT NULL,
    last_update_date datetime     NOT NULL,
    operation        VARCHAR(10)  NOT NULL,
    PRIMARY KEY (tag_id)
);

CREATE TABLE gift_certificates_tags
(
    gift_certificate_tag_id bigint NOT NULL AUTO_INCREMENT,
    gift_certificate_id     bigint NOT NULL,
    tag_id                  bigint NOT NULL,
    FOREIGN KEY (gift_certificate_id) REFERENCES gift_certificates (gift_certificate_id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tags (tag_id) ON DELETE CASCADE,
    PRIMARY KEY (gift_certificate_tag_id)
);

CREATE TABLE users
(
    user_id          bigint       NOT NULL AUTO_INCREMENT,
    user_name        VARCHAR(40)  NOT NULL,
    create_date      datetime     NOT NULL,
    last_update_date datetime     NOT NULL,
    operation        VARCHAR(10)  NOT NULL,
    password         VARCHAR(60)  NOT NULL,
    is_active         bit          NOT NULL,
    role             varchar(20) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE orders
(
    order_id                  BIGINT         NOT NULL AUTO_INCREMENT,
    price                     DECIMAL(10, 2) NOT NULL,
    create_date               datetime       NOT NULL,
    user_id                   BIGINT         NOT NULL,
    order_gift_certificate_id BIGINT,
    last_update_date          datetime       NOT NULL,
    operation                 VARCHAR(10)    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (order_gift_certificate_id) REFERENCES gift_certificates (gift_certificate_id) ON DELETE SET NULL,
    PRIMARY KEY (order_id)
);
