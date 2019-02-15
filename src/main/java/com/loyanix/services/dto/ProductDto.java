package com.loyanix.services.dto;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String gender;
    private String size;
    private Integer quantity;

    public ProductDto(String name, BigDecimal price, String gender, String size, Integer quantity) {
        this.name = name;
        this.price = price;
        this.gender = gender;
        this.size = size;
        this.quantity = quantity;
    }

    public ProductDto(Long id, String name, BigDecimal price, String gender, String size, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.gender = gender;
        this.size = size;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", gender='" + gender + '\'' +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
