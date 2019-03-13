package com.loyanix.services.impl;

import com.loyanix.dao.ProductDao;
import com.loyanix.domain.Product;
import com.loyanix.services.ProductService;
import com.loyanix.services.converter.ProductConverter;
import com.loyanix.services.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private ProductConverter productConverter;

    public ProductServiceImpl(ProductDao productDao, ProductConverter productConverter) {
        this.productDao = productDao;
        this.productConverter = productConverter;
    }

    public ProductServiceImpl() {
    }

    @Override
    public void create(ProductDto productDto) {
        productDao.create(productConverter.toEntity(productDto));
    }

    @Override
    public ProductDto getById(Long id) {
        return productConverter.toDto(productDao.getById(id));
    }

    @Override
    public void update(ProductDto productDto) {
        productDao.update(productConverter.toEntity(productDto));
    }


    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productDao.findAll();
        return products.stream()
                .map(product -> productConverter.toDto(product))
                .collect(Collectors.toList());
    }
}
