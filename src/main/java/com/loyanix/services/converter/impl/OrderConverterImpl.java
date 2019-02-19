package com.loyanix.services.converter.impl;

import com.loyanix.domain.Order;
import com.loyanix.services.converter.ClientConverter;
import com.loyanix.services.converter.OrderConverter;
import com.loyanix.services.converter.ProductConverter;
import com.loyanix.services.dto.OrderDto;

public class OrderConverterImpl implements OrderConverter {

    private ClientConverter clientConverter;
    private ProductConverter productConverter;

    public OrderConverterImpl(ClientConverter clientConverter, ProductConverter productConverter) {
        this.clientConverter = clientConverter;
        this.productConverter = productConverter;
    }

    @Override
    public Order toEntity(OrderDto orderDto) {
        return new Order(orderDto.getId(),
                clientConverter.toEntity(orderDto.getClient()),
                productConverter.productsToEntity(orderDto.getProducts()),
                orderDto.getOrderPrice(),
                orderDto.getDateOfCreate());
    }

    @Override
    public OrderDto toDto(Order order) {
        return new OrderDto(order.getId(),
                clientConverter.toDto(order.getClient()),
                productConverter.productsToDto(order.getProducts()),
                order.getOrderPrice(),
                order.getDateOfCreate());
    }
}
