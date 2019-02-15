package com.loyanix.services.converter.impl;

import com.loyanix.domain.Product;
import com.loyanix.services.converter.ProductConverter;
import com.loyanix.services.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

public class ProductConverterImpl implements ProductConverter {
    @Override
    public Product toEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getPrice(), productDto.getGender(),
                productDto.getSize(), productDto.getQuantity());
    }

    @Override
    public ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getGender(),
                product.getSize(), product.getQuantity());
    }

    public List<Product> productsToEntity(List<ProductDto> productDtos) {
        List<Product> products = new ArrayList<>();
        for (ProductDto productDto : productDtos) {
            products.add(this.toEntity(productDto));
        }
        return products;
    }

    public List<ProductDto> productsToDto(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(this.toDto(product));
        }
        return productDtos;
    }

}
