package com.loyanix.services;

import com.loyanix.services.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void create(OrderDto orderDto);

    OrderDto getById(Long id);

    void update(Long id, OrderDto orderDto);

    void delete(Long id);

    List<OrderDto> findAll();

    List<OrderDto> findAllByClient(Long userId);
}
