package com.loyanix.services.impl;

import com.loyanix.dao.ProductDao;
import com.loyanix.domain.Product;
import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.ProductService;
import com.loyanix.services.converter.ProductConverter;
import com.loyanix.services.dto.ProductDto;
import com.loyanix.validator.ValidationService;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private ProductConverter productConverter;
    private ValidationService validationService;

    public ProductServiceImpl(ProductDao productDao, ProductConverter productConverter, ValidationService validationService) {
        this.productDao = productDao;
        this.productConverter = productConverter;
        this.validationService = validationService;
    }

    @Override
    public void create(ProductDto productDto) {
        productDao.create(productConverter.toEntity(productDto));
    }

    @Override
    public ProductDto getById(Long id) throws BusinessException {
        validationService.validateId(this, id);
        return productConverter.toDto(productDao.getById(id));
    }

    @Override
    public void update(Long id, ProductDto productDto) {
        productDao.update(id, productConverter.toEntity(productDto));
    }


    @Override
    public void delete(Long id) throws BusinessException {
        validationService.validateId(this, id);
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
