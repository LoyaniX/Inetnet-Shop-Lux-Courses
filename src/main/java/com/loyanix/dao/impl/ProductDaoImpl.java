package com.loyanix.dao.impl;

import com.loyanix.dao.ProductDao;
import com.loyanix.domain.Client;
import com.loyanix.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {

    private Map<Long, Product> productMap = new HashMap<>();

    @Override
    public void create(Product product) {
        productMap.put(product.getId(), product);
    }

    @Override
    public void getById(Long id) {
        productMap.get(id);
    }

    @Override
    public void update(Long id, Product product) {
        productMap.replace(id, product);
    }

    @Override
    public void delete(Long id) {
        productMap.remove(id);
    }

    @Override
    public Map<Long, Product> findAll() {
        return productMap;
    }
}
