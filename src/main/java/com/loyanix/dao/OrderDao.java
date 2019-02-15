package com.loyanix.dao;

import com.loyanix.domain.Order;

import java.util.List;

public interface OrderDao {

    void create(Order order);

    Order getById(Long id);

    void update(Long id, Order order);

    void delete(Long id);

    List<Order> findAll();

    List<Order> findAllByClient(Long userId);
}
