package com.dev.cinema.dao;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AbstractDao<T> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);

    public T add(T entity, Class<T> clazz) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
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
            logger.info("Entity was added successfully: " + entity);
        }
    }
}
