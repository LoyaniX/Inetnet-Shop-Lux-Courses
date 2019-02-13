package com.loyanix.services.impl;

import com.loyanix.dao.OrderDao;
import com.loyanix.dao.impl.OrderDaoImpl;
import com.loyanix.domain.Order;
import com.loyanix.services.OrderService;
import com.loyanix.services.converter.OrderCoverter;
import com.loyanix.services.converter.impl.OrderConverterImpl;
import com.loyanix.services.dto.OrderDto;
import com.loyanix.services.dto.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderCoverter orderCoverter = new OrderConverterImpl();

    @Override
    public void create(OrderDto orderDto) {
        orderDto.setDateOfCreate(new Date());
        orderDto.setOrderPrice(countCost(orderDto));
        orderDao.create(orderCoverter.toEntity(orderDto));
    }

    @Override
    public OrderDto getById(Long id) {
        return orderCoverter.toDto(orderDao.getById(id));
    }

    @Override
    public void update(Long id, OrderDto orderDto) {
        orderDto.setDateOfCreate(new Date());
        orderDao.update(id, orderCoverter.toEntity(orderDto));
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
            orderDtos.add(orderCoverter.toDto(order));
        }
        return orderDtos;
    }

    @Override
    public List<OrderDto> findAllOfClient(Long userId) {
        List<Order> orders = orderDao.findAllOfClient(userId);
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            orderDtos.add(orderCoverter.toDto(order));
        }
        return orderDtos;
    }

    private BigDecimal countCost(OrderDto orderDto) {
        BigDecimal orderCost = new BigDecimal(0);
        List<ProductDto> productDtos = orderDto.getProducts();
        for (ProductDto productDto : productDtos) {
            orderCost = orderCost.add(productDto.getPrice().multiply(BigDecimal.valueOf(productDto.getQuantity())));
        }
        return orderCost;
    }
}
