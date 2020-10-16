package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    @Override
    public Order add(Order order) {
        return super.add(order, Order.class);
    }

    @Override
    public List<Order> getAllUserOrders(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
