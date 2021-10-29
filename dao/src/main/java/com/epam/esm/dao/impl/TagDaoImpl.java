package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.sql.TagSql;
import com.epam.esm.entity.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * TagDao implementation.
 *
 * @author Yauheni Tsitov
 */
@Repository
public class TagDaoImpl implements TagDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public TagDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Tag add(Tag tag) {
        String tagName = tag.getName();
        Long tagId = (Long) sessionFactory.openSession().save(tag);
        return new Tag(tagId, tagName);
    }

    @Override
    public List<Tag> findAll() {
        return sessionFactory.openSession().createSQLQuery(TagSql.FIND_ALL).addEntity(Tag.class).list();
    }

    @Override
    public Optional<Tag> findByName(String name) {
        List<Tag> tagList = (List<Tag>) sessionFactory.openSession().createSQLQuery(TagSql.FIND_BY_NAME).setParameter(1, name).addEntity(Tag.class).list();
        return returnTag(tagList);
    }

    @Override
    public Optional<Tag> findById(long id) {
        Session session = sessionFactory.openSession();
        Tag tag = session.find(Tag.class, id);
        return Optional.ofNullable(tag);
    }

    @Override
    public void update(Tag tag) {
        sessionFactory.openSession().update(tag);
    }

    @Override
    public void delete(Tag tag) {
        sessionFactory.openSession().delete(tag);
    }

    @Override
    public Tag findMostWidelyTagUsersHighestCostOrders() {
        Session session = sessionFactory.openSession();
        List<Tag> list = session.createSQLQuery("SELECT tags.* FROM users LEFT JOIN orders ON users.user_id=orders.user_id LEFT JOIN gift_certificates ON gift_certificates.gift_certificate_id=orders.order_gift_certificate_id LEFT JOIN gift_certificates_tags ON gift_certificates_tags.gift_certificate_id=gift_certificates.gift_certificate_id LEFT JOIN tags ON tags.tag_id=gift_certificates_tags.tag_id WHERE users.user_id= (SELECT user_id FROM orders GROUP BY user_id ORDER BY SUM(price) DESC LIMIT 1) group by tag_id ORDER BY COUNT(tags.tag_id) DESC LIMIT 1").addEntity(Tag.class).list();
        return list.get(0);
    }

    private Optional<Tag> returnTag(List<Tag> tagList) {
        if (tagList.size() != 0) {
            return Optional.of(tagList.get(0));
        } else {
            return Optional.empty();
        }
    }
}
