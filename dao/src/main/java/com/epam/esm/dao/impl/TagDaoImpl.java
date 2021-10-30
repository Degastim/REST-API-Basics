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
        if (tagList.size() != 0) {
            return Optional.of(tagList.get(0));
        } else {
            return Optional.empty();
        }
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
    public Optional<Tag> findMostWidelyTagUsersHighestCostOrders() {
        Session session = sessionFactory.openSession();
        List<Tag> tagList = session.createSQLQuery(TagSql.FIND_MOST_WIDELY_TAG_USERS_HIGHEST_COST_ORDERS).addEntity(Tag.class).list();
        if (tagList.size() != 0) {
            return Optional.of(tagList.get(0));
        } else {
            return Optional.empty();
        }
    }
}
