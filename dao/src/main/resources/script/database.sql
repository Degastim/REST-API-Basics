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
CREATE TABLE user_roles
(
    user_role_id   BIGINT      NOT NULL AUTO_INCREMENT,
    user_role_name VARCHAR(50) NOT NULL,
    create_date           datetime       NOT NULL,
    last_update_date      datetime       NOT NULL,
    operation             VARCHAR(10)    NOT NULL,
    PRIMARY KEY (user_role_id)
);
CREATE TABLE users
(
    user_id          bigint      NOT NULL AUTO_INCREMENT,
    user_name        VARCHAR(40) NOT NULL,
    create_date      datetime    NOT NULL,
    last_update_date datetime    NOT NULL,
    operation        VARCHAR(10) NOT NULL,
    password         VARCHAR(60) NOT NULL,
    is_active        bit         NOT NULL,
    user_role        BIGINT      NOT NULL,
    FOREIGN KEY (user_role) REFERENCES user_roles(user_role_id),
    PRIMARY KEY (user_id)
);
CREATE TABLE permissions
(
    permission_id   bigint      NOT NULL AUTO_INCREMENT,
    permission_name VARCHAR(50) NOT NULL,
    create_date           datetime       NOT NULL,
    last_update_date      datetime       NOT NULL,
    operation             VARCHAR(10)    NOT NULL,
    PRIMARY KEY (permission_id)
);

CREATE TABLE user_roles_permissions
(
    user_role_permission_id BIGINT NOT NULL AUTO_INCREMENT,
    user_role_id            BIGINT NOT NULL,
    permission_id           BIGINT NOT NULL,
    FOREIGN KEY (user_role_id) REFERENCES user_roles (user_role_id),
    FOREIGN KEY (permission_id) REFERENCES permissions (permission_id),
    PRIMARY KEY (user_role_permission_id)
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
