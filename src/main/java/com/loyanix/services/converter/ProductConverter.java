package com.loyanix.services.converter;

import com.loyanix.domain.Product;
import com.loyanix.services.dto.ProductDto;

public interface ProductConverter {

    Product toEntity(ProductDto productDto);
    ProductDto toDto(Product product);
}
