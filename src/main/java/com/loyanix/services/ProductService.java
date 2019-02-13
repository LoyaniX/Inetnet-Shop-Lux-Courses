package com.loyanix.services;

import com.loyanix.services.dto.ProductDto;

import java.util.List;

public interface ProductService {

    void create(ProductDto productDto);

    ProductDto getById(Long id);

    void update(Long id, ProductDto productDto);

    void delete(Long id);

    List<ProductDto> findAll();
}
