package com.loyanix.services.converter.impl;

import com.loyanix.domain.Order;
import com.loyanix.services.converter.OrderCoverter;
import com.loyanix.services.dto.OrderDto;

public class OrderConverterImpl implements OrderCoverter {



    @Override
    public Order toEntity(OrderDto orderDto) {
        return new Order(null,
                orderDto.getClient(),
                orderDto.getProducts(),
                orderDto.getOrderPrice(),
                orderDto.getDateOfCreate());
    }

    @Override
    public OrderDto toDto(Order order) {
        return new OrderDto(order.getId(),
                order.getClient(),
                order.getProducts(),
                order.getOrderPrice(),
                order.getDateOfCreate());
    }
}
