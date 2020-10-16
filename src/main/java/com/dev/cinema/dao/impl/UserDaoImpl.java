package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.UserDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Dao
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Override
    public User add(User user) {
        return super.add(user, User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User u where email = :email");
            query.setParameter("email", email);
            return query.uniqueResultOptional();
        }
    }
}
