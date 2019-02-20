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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        return orders.stream()
                .map(order -> orderConverter.toDto(order))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllByClient(Long userId) {
        List<Order> orders = orderDao.findAllByClient(userId);
        return orders.stream()
                .map(order -> orderConverter.toDto(order))
                .collect(Collectors.toList());
    }

    private BigDecimal calculateCost(OrderDto orderDto) {
        BigDecimal orderCost = BigDecimal.ZERO;
        List<ProductDto> productDtos = orderDto.getProducts();
        for (ProductDto productDto : productDtos) {
            orderCost = productDto.getPrice().multiply(BigDecimal.valueOf(productDto.getQuantity())).add(orderCost);
        }
        return orderCost;
    }
}
