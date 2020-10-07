package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add movie session", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> getAvailableMovieSessions(Long id, LocalDate date) {
        LocalDate from = LocalDate.from(date.atStartOfDay());
        LocalDate to = LocalDate.from(date.atTime(  LocalTime.MAX));
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query =
                    session.createQuery("FROM MovieSession "
                                    + "WHERE id = :id "
                                    + "AND showtime "
                                    + "BETWEEN :from AND :to", MovieSession.class);
            query.setParameter("id", id);
            query.setParameter("from", from);
            query.setParameter("to", to);
            return query.getResultList();
        }
    }
}
