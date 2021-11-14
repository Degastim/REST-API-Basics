package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.sql.TagSql;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * TagDao implementation.
 *
 * @author Yauheni Tsitov
 */
@Repository
public class TagDaoImpl implements TagDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Tag tag) {
        entityManager.persist(tag);
    }

    @Override
    public List<Tag> findAll(PaginationContainer paginationContainer) {
        int page = paginationContainer.getPage();
        int size = paginationContainer.getSize();
        int previousPageEnd = (page - 1) * size;
        List<Tag> list;
        if (size == 0 && page == 0) {
            list = entityManager.createNativeQuery(TagSql.FIND_ALL, Tag.class).getResultList();
        } else {
            list = entityManager.createNativeQuery(TagSql.FIND_ALL_WITH_LIMIT, Tag.class)
                    .setParameter(1, previousPageEnd).setParameter(2, size).getResultList();
        }
        return list;
    }

    @Override
    public Optional<Tag> findByName(String name) {
        List<Tag> tagList = (List<Tag>) entityManager.createNativeQuery(TagSql.FIND_BY_NAME, Tag.class)
                .setParameter(1, name).getResultList();
        return returnTag(tagList);
    }

    @Override
    public Optional<Tag> findById(long id) {
        Tag tag = entityManager.find(Tag.class, id);
        return Optional.ofNullable(tag);
    }

    @Override
    public void delete(Tag tag) {
        entityManager.remove(tag);
    }

    @Override
    public Optional<Tag> findMostWidelyTagUsersHighestCostOrders() {
        List<Tag> tagList = entityManager.createNativeQuery(TagSql.FIND_MOST_WIDELY_TAG_USERS_HIGHEST_COST_ORDERS, Tag.class)
                .getResultList();
        return returnTag(tagList);
    }

    private Optional<Tag> returnTag(List<Tag> tagList) {
        if (tagList.size() != 0) {
            return Optional.of(tagList.get(0));
        } else {
            return Optional.empty();
        }
    }
}
