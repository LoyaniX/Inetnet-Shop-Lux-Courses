package com.loyanix.services.converter.impl;

import com.loyanix.domain.Order;
import com.loyanix.domain.Product;
import com.loyanix.services.converter.ClientConverter;
import com.loyanix.services.converter.OrderCoverter;
import com.loyanix.services.converter.ProductConverter;
import com.loyanix.services.dto.OrderDto;
import com.loyanix.services.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

public class OrderConverterImpl implements OrderCoverter {

    private ClientConverter clientConverter = new ClientConverterImpl();
    private ProductConverter productConverter = new ProductConverterImpl();

    @Override
    public Order toEntity(OrderDto orderDto) {
        return new Order(null,
                clientConverter.toEntity(orderDto.getClient()),
                productsToEntity(orderDto.getProducts()),
                orderDto.getOrderPrice(),
                orderDto.getDateOfCreate());
    }

    @Override
    public OrderDto toDto(Order order) {
        return new OrderDto(order.getId(),
                clientConverter.toDto(order.getClient()),
                productsToDto(order.getProducts()),
                order.getOrderPrice(),
                order.getDateOfCreate());
    }

    private List<Product> productsToEntity(List<ProductDto> productDtos) {
        List<Product> products = new ArrayList<>();
        for (ProductDto productDto : productDtos) {
            products.add(productConverter.toEntity(productDto));
        }
        return products;
    }

    private List<ProductDto> productsToDto(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(productConverter.toDto(product));
        }
        return productDtos;
    }
}
