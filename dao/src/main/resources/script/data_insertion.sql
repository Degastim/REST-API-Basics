INSERT INTO tags (tag_id ,tag_name) VALUES('1','a');
INSERT INTO tags (tag_id ,tag_name) VALUES('2','b');
INSERT INTO tags (tag_id ,tag_name) VALUES('3','c');
INSERT INTO tags (tag_id ,tag_name) VALUES('4','d');
INSERT INTO tags (tag_id ,tag_name) VALUES('5','e');

INSERT INTO gift_certificates (gift_certificate_id, gift_certificate_name, description, price, duration, create_date,last_update_date)
VALUES(1, 'Dig', 'Big price', 151, 67, '2013-09-13T12:12:12', '2013-9-12T12:12:12');

INSERT INTO gift_certificates (gift_certificate_id, gift_certificate_name, description, price, duration, create_date,last_update_date)
VALUES(2, 'Red', 'Red price', 43, 13, '2020-03-14T23:42:11.164','2020-05-05T23:42:12.112');

INSERT INTO gift_certificates (gift_certificate_id, gift_certificate_name, description, price, duration, create_date,last_update_date)
VALUES(3, 'City', 'Large city', 464, 13, '2021-02-15T08:42:11.167', '2021-05-10T08:42:10.145');

INSERT INTO gift_certificates (gift_certificate_id, gift_certificate_name, description, price, duration, create_date,last_update_date)
VALUES(4, 'Plane', 'Fly plain', 15.15, 13, '2016-05-16T16:11:21.149', '2016-01-29T16:30:21.211');

INSERT INTO gift_certificates (gift_certificate_id, gift_certificate_name, description, price, duration, create_date,last_update_date)
VALUES(5, 'Train', 'Red train', 151, 16, '2019-07-19T11:10:11.148', '2019-11-19T11:10:11.111');

INSERT INTO gift_certificates (gift_certificate_id, gift_certificate_name, description, price, duration, create_date,last_update_date)
VALUES(6, 'BMV', 'Fast and comfortable car',1246 ,300, '2022-10-25T11:11:11', '2019-11-19T11:10:11');

INSERT INTO gift_certificates_tags (gift_certificate_tag_id, gift_certificate_id, tag_id) VALUES (1, 1, 1);
INSERT INTO gift_certificates_tags (gift_certificate_tag_id, gift_certificate_id, tag_id) VALUES (2, 1, 2);
INSERT INTO gift_certificates_tags (gift_certificate_tag_id, gift_certificate_id, tag_id) VALUES (3, 3, 3);
INSERT INTO gift_certificates_tags (gift_certificate_tag_id, gift_certificate_id, tag_id) VALUES (4, 3, 4);
INSERT INTO gift_certificates_tags (gift_certificate_tag_id, gift_certificate_id, tag_id) VALUES (5, 4, 4);