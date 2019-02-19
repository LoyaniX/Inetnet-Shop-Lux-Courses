package com.loyanix.services;

import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void create(OrderDto orderDto);

    OrderDto getById(Long id) throws BusinessException;

    void update(Long id, OrderDto orderDto) throws BusinessException;

    void delete(Long id);

    List<OrderDto> findAll();

    List<OrderDto> findAllByClient(Long userId);
}
