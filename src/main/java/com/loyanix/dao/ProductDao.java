package com.loyanix.dao;

import com.loyanix.domain.Product;

import java.util.List;

public interface ProductDao {

    void create(Product product);

    Product getById(Long id);

    void update(Product product);

    void delete(Long id);

    List<Product> findAll();
}
