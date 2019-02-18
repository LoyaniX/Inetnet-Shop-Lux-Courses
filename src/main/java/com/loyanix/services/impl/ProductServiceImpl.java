package com.loyanix.services.impl;

import com.loyanix.dao.ProductDao;
import com.loyanix.dao.impl.ProductDaoImpl;
import com.loyanix.domain.Product;
import com.loyanix.services.ProductService;
import com.loyanix.services.converter.ProductConverter;
import com.loyanix.services.converter.impl.ProductConverterImpl;
import com.loyanix.services.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private ProductConverter productConverter;

    public ProductServiceImpl(ProductDao productDao, ProductConverter productConverter) {
        this.productDao = productDao;
        this.productConverter = productConverter;
    }

    @Override
    public void create(ProductDto productDto) {
        productDao.create(productConverter.toEntity(productDto));
    }

    @Override
    public ProductDto getById(Long id) {
        try {
            return productConverter.toDto(productDao.getById(id));
        } catch (Exception e) {
            System.out.println("Product with id " + id + " is absent");
            e.getMessage();
            return null;
        }
    }

    @Override
    public void update(Long id, ProductDto productDto) {
        productDao.update(id, productConverter.toEntity(productDto));
    }


    @Override
    public void delete(Long id) {
        try {
            productDao.delete(id);
        } catch (Exception e) {
            System.out.println("Product with id " + id + " is absent");
            e.getMessage();
        }
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productDao.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            productDtoList.add(productConverter.toDto(product));
        }
        return productDtoList;
    }
}
