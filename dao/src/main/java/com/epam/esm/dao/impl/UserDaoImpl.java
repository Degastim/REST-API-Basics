package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.constant.sql.UserSql;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll(PaginationContainer paginationContainer) {
        Session session = sessionFactory.openSession();
        int page = paginationContainer.getPage();
        int size = paginationContainer.getSize();
        int previousPageEnd = (page - 1) * size;
        List<User> list;
        if (size == 0 && page == 0) {
            list = session.createSQLQuery(UserSql.FIND_ALL).addEntity(User.class).list();
        } else {
            list = session.createSQLQuery(UserSql.FIND_ALL_WITH_LIMIT).addEntity(User.class)
                    .setParameter(1, previousPageEnd).setParameter(2, size).list();
        }
        return list;
    }

    @Override
    public Optional<User> findById(long id) {
        Session session = sessionFactory.openSession();
        User user = session.find(User.class, id);
        return Optional.ofNullable(user);
    }
}
