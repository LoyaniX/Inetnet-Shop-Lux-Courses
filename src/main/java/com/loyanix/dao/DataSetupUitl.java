package com.loyanix.dao;

import com.loyanix.services.ClientService;
import com.loyanix.services.OrderService;
import com.loyanix.services.ProductService;
import com.loyanix.services.dto.ClientDto;
import com.loyanix.services.dto.OrderDto;
import com.loyanix.services.dto.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DataSetupUitl {

    public static void addExampleData(ClientService clientService, ProductService productService, OrderService orderService) {
        productService.create(new ProductDto("Tesla", new BigDecimal(123456789), "unisex", "Model X", 12));
        productService.create(new ProductDto("Tesla", new BigDecimal(2412312), "male", "Model S", 123));
        productService.create(new ProductDto("Iphone", new BigDecimal(1000), "unisex", "Xs max", 56));
        productService.create(new ProductDto("T-short", new BigDecimal(12), "female", "xs", 120));

        clientService.create(new ClientDto("Max", "Loyan", 21, "loyanmaksim@gmail.com", ""));
        clientService.create(new ClientDto("Maksim", "Loyan", 25, "", ""));
        clientService.create(new ClientDto("John", "Shelvy", 12, "", "0995696886"));
        clientService.create(new ClientDto("Marina", "Kayl", 65, "loyanmaksim@gmail.com", "0995696886"));

        List<ProductDto> exampleProducts = productService.findAll();
        for (long i = 1; i < 5; i++) {
            orderService.create(new OrderDto(clientService.getById(i), exampleProducts, null, null));
        }
    }
}
