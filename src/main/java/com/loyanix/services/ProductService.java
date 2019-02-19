package com.loyanix.services;

import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.dto.ProductDto;

import java.util.List;

public interface ProductService {

    void create(ProductDto productDto);

    ProductDto getById(Long id) throws BusinessException;

    void update(Long id, ProductDto productDto);

    void delete(Long id) throws BusinessException;

    List<ProductDto> findAll();
}
