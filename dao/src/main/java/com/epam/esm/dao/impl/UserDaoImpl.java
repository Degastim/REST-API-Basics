package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.constant.sql.UserSql;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.user.User;
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

    @Override
    public Optional<User> findByName(String name) {
        List<User> userList = entityManager.createNativeQuery(UserSql.FIND_NAME, User.class)
                .setParameter(1, name).getResultList();
        return returnUser(userList);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    private Optional<User> returnUser(List<User> userList) {
        if (userList.size() != 0) {
            return Optional.of(userList.get(0));
        } else {
            return Optional.empty();
        }
    }
}
