package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.constant.sql.UserSql;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll(PaginationContainer paginationContainer) {
        int page = paginationContainer.getPage();
        int size = paginationContainer.getSize();
        int previousPageEnd = (page - 1) * size;
        List<User> list;
        if (size == 0 && page == 0) {
            list = entityManager.createNativeQuery(UserSql.FIND_ALL, User.class).getResultList();
        } else {
            list = entityManager.createNativeQuery(UserSql.FIND_ALL_WITH_LIMIT, User.class)
                    .setParameter(1, previousPageEnd).setParameter(2, size).getResultList();
        }
        return list;
    }

    @Override
    public Optional<User> findById(long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }
}
