CREATE TABLE gift_certificates
(
    gift_certificate_id   bigint         NOT NULL AUTO_INCREMENT,
    gift_certificate_name varchar(256)   NOT NULL UNIQUE,
    description           text           NOT NULL,
    price                 decimal(10, 0) NOT NULL,
    duration              int            NOT NULL,
    create_date           datetime       NOT NULL,
    last_update_date      datetime       NOT NULL,
    PRIMARY KEY (gift_certificate_id)
);

CREATE TABLE tags
(
    tag_id  bigint        NOT NULL AUTO_INCREMENT,
    tag_name varchar(256) NOT NULL UNIQUE,
    PRIMARY KEY (tag_id)
);

CREATE TABLE gift_certificates_tags
(
    gift_certificate_tag_id bigint NOT NULL AUTO_INCREMENT,
    gift_certificate_id     bigint NOT NULL REFERENCES gift_certificates (gift_certificate_id),
    tag_id                  bigint NOT NULL REFERENCES tags (tag_id),
    PRIMARY KEY (gift_certificate_tag_id)
);