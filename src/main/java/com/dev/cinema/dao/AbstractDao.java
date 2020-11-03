package com.dev.cinema.dao;

import com.dev.cinema.exception.DataProcessingException;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Log4j
public abstract class AbstractDao<T> {
    protected final SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T add(T entity, Class<T> clazz) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            log.info("Entity was added successfully: " + entity);
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add new "
                    + clazz.getSimpleName() + " entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
