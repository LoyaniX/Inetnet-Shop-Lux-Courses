package com.loyanix.services.converter;

        import com.loyanix.domain.Product;
        import com.loyanix.services.dto.ProductDto;

        import java.util.List;

public interface ProductConverter {

    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);

    List<Product> productsToEntity(List<ProductDto> productDtos);

    List<ProductDto> productsToDto(List<Product> products);
}
