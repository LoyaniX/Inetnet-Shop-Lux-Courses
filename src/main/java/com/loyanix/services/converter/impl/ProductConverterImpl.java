package com.loyanix.services.converter.impl;

import com.loyanix.domain.Product;
import com.loyanix.services.converter.ProductConverter;
import com.loyanix.services.dto.ProductDto;

public class ProductConverterImpl implements ProductConverter {
    @Override
    public Product toEntity(ProductDto productDto) {
        return new Product(null, productDto.getName(), productDto.getPrice(), productDto.getGender(),
                productDto.getSize(), productDto.getQuantity());
    }

    @Override
    public ProductDto toDto(Product product) {
        return new ProductDto(product.getName(), product.getPrice(), product.getGender(),
                product.getSize(), product.getQuantity());
    }
}
