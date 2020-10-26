package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.CinemaHall;
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
public class CinemaHallDaoImpl extends AbstractDao<CinemaHall>
        implements CinemaHallDao {

    @Autowired
    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return super.add(cinemaHall, CinemaHall.class);
    }

    @Override
    public Optional<CinemaHall> getById(Long id) {
        log.info("Getting cinema hall. . .");
        try (Session session = sessionFactory.openSession()) {
            return Optional.of(sessionFactory.openSession().get(CinemaHall.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get cinema hall with id " + id, e);
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<CinemaHall> query =
                    session.createQuery("from CinemaHall ", CinemaHall.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all halls", e);
        }
    }
}
