package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class MovieSessionDaoImpl extends AbstractDao<MovieSession>
        implements MovieSessionDao {
    private static final Logger logger = Logger.getLogger(MovieSessionDaoImpl.class);

    @Autowired
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return super.add(movieSession, MovieSession.class);
    }

    @Override
    public List<MovieSession> getAvailableMovieSessions(Long id, LocalDate date) {
        log.info("Getting movie session. . .");
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query =
                    session.createQuery("FROM MovieSession "
                                    + "WHERE id = :id AND showtime "
                                    + "BETWEEN :from AND :to", MovieSession.class);
            query.setParameter("id", id);
            query.setParameter("from", date.atTime(LocalTime.MIN));
            query.setParameter("to", date.atTime(LocalTime.MAX));
            return query.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<MovieSession> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.of(session.get(MovieSession.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get session with id " + id, e);
        }
    }
}
