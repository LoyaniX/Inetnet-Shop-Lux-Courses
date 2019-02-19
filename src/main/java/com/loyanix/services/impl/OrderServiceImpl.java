package com.loyanix.services.impl;

import com.loyanix.dao.OrderDao;
import com.loyanix.domain.Order;
import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.OrderService;
import com.loyanix.services.converter.OrderConverter;
import com.loyanix.services.dto.OrderDto;
import com.loyanix.services.dto.ProductDto;
import com.loyanix.validator.ValidationService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private OrderConverter orderConverter;
    private ValidationService validationService;

    public OrderServiceImpl(OrderDao orderDao, OrderConverter orderConverter, ValidationService validationService) {
        this.orderDao = orderDao;
        this.orderConverter = orderConverter;
        this.validationService = validationService;
    }

    @Override
    public void create(OrderDto orderDto) {
        orderDto.setDateOfCreate(new Date());
        orderDto.setOrderPrice(calculateCost(orderDto));
        orderDao.create(orderConverter.toEntity(orderDto));
    }

    @Override
    public OrderDto getById(Long id) throws BusinessException {
        validationService.validateId(this, id);
        return orderConverter.toDto(orderDao.getById(id));
    }

    @Override
    public void update(Long id, OrderDto orderDto) throws BusinessException {
        validationService.validateId(this, id);
        orderDto.setDateOfCreate(new Date());
        orderDao.update(id, orderConverter.toEntity(orderDto));
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public List<OrderDto> findAll() {
        List<Order> orders = orderDao.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            orderDtos.add(orderConverter.toDto(order));
        }
        return orderDtos;
    }

    @Override
    public List<OrderDto> findAllByClient(Long userId) {
        List<Order> orders = orderDao.findAllByClient(userId);
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            orderDtos.add(orderConverter.toDto(order));
        }
        return orderDtos;
    }

    private BigDecimal calculateCost(OrderDto orderDto) {
        BigDecimal orderCost = BigDecimal.ZERO;
        List<ProductDto> productDtos = orderDto.getProducts();
        for (ProductDto productDto : productDtos) {
            orderCost = orderCost.add(productDto.getPrice().multiply(BigDecimal.valueOf(productDto.getQuantity())));
        }
        return orderCost;
    }
}
