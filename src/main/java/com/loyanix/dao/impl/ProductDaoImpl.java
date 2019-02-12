package com.loyanix.dao.impl;

import com.loyanix.dao.ProductDao;
import com.loyanix.domain.Client;
import com.loyanix.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {

    private Map<Long, Product> productMap = new HashMap<>();
    private Long id = 1L;

    @Override
    public void create(Product product) {
        product.setId(id++);
        productMap.put(product.getId(), product);
    }

    @Override
    public Product getById(Long id) {
        return productMap.get(id);
    }

    @Override
    public void update(Long id, Product product) {
        product.setId(id);
        productMap.replace(product.getId(), product);
    }

    @Override
    public void delete(Long id) {
        productMap.remove(id);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }
}
