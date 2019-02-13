package com.loyanix.services.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDto {

    private Long id;
    private ClientDto client;
    private List<ProductDto> products;
    private BigDecimal orderPrice;
    private Date dateOfCreate;

    public OrderDto() {
    }

    public OrderDto(ClientDto client, List<ProductDto> products, BigDecimal orderPrice, Date dateOfCreate) {
        this.client = client;
        this.products = products;
        this.orderPrice = orderPrice;
        this.dateOfCreate = dateOfCreate;
    }

    public OrderDto(Long id, ClientDto client, List<ProductDto> products, BigDecimal orderPrice, Date dateOfCreate) {
        this.id = id;
        this.client = client;
        this.products = products;
        this.orderPrice = orderPrice;
        this.dateOfCreate = dateOfCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", client=" + client +
                ", products=" + products +
                ", orderPrice=" + orderPrice +
                ", dateOfCreate=" + dateOfCreate +
                '}';
    }
}
