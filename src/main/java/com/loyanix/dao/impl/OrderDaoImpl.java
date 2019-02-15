package com.loyanix.dao.impl;

import com.loyanix.dao.OrderDao;
import com.loyanix.domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {

    private Map<Long, Order> orderMap = new HashMap<>();
    private long id = 1L;

    @Override
    public void create(Order order) {
        order.setId(id++);
        orderMap.put(order.getId(), order);
    }

    @Override
    public Order getById(Long id) throws NullPointerException {
        return orderMap.get(id);
    }

    @Override
    public void update(Long id, Order order) {
        order.setId(id);
        orderMap.replace(order.getId(), order);
    }

    @Override
    public void delete(Long id) throws NullPointerException {
        orderMap.remove(id);
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orderMap.values());
    }

    @Override
    public List<Order> findAllByClient(Long usedId) throws NullPointerException {
        List<Order> orderList = findAll();
        List<Order> ordersOfClient = new ArrayList<>();
        for (Order order : orderList) {
            if (usedId.equals(order.getClient().getId())) {
                ordersOfClient.add(order);
            }
        }
        return ordersOfClient;
    }
}
