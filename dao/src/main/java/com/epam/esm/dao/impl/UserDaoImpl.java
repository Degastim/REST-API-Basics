package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
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
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        return (List<User>) session.createSQLQuery("SELECT * FROM users").addEntity(User.class).list();
    }

    @Override
    public Optional<User> findById(long id) {
        Session session = sessionFactory.openSession();
        User user=session.find(User.class, id);
        return Optional.ofNullable(user);
    }
}
