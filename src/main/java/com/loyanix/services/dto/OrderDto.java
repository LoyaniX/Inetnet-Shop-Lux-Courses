package com.loyanix.services.dto;

import com.loyanix.domain.Client;
import com.loyanix.domain.Product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDto {

    private Long id;
    private Client client;
    private List<Product> products;
    private BigDecimal orderPrice;
    private Date dateOfCreate;

    public OrderDto() {
    }

    public OrderDto(Client client, List<Product> products, BigDecimal orderPrice, Date dateOfCreate) {
        this.client = client;
        this.products = products;
        this.orderPrice = orderPrice;
        this.dateOfCreate = dateOfCreate;
    }

    public OrderDto(Long id, Client client, List<Product> products, BigDecimal orderPrice, Date dateOfCreate) {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
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
