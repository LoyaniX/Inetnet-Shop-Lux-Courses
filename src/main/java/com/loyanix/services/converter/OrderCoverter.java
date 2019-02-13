package com.loyanix.services.converter;

import com.loyanix.domain.Order;
import com.loyanix.services.dto.OrderDto;

public interface OrderCoverter {

    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);
}
