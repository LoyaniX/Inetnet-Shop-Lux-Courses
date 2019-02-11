package com.loyanix.dao;

import com.loyanix.domain.Client;
import com.loyanix.domain.Product;

import java.util.Map;

public interface ProductDao {

    void create(Product product);
    void getById(Long id);
    void update(Long id, Product product);
    void delete(Long id);
    Map<Long, Product> findAll();
}
