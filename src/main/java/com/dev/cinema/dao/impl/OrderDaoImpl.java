package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Order add(Order order) {
        return super.add(order, Order.class);
    }

    @Override
    public List<Order> getAllUserOrders(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query =
                    session.createQuery(
                            "select distinct o from Order o "
                                    + "join fetch o.tickets "
                                    + "where o.user = :user", Order.class)
                    .setParameter("user", user);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
