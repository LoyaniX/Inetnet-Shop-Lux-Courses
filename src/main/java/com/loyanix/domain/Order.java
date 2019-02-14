package com.loyanix.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {

    private Long id;
    private Client client;
    private List<Product> products;
    private BigDecimal orderPrice;
    private Date dateOfCreate;

    public Order(Long id, Client client, List<Product> products, BigDecimal orderPrice, Date dateOfCreate) {
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
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", products=" + products +
                ", orderPrice=" + orderPrice +
                ", dateOfCreate=" + dateOfCreate +
                '}';
    }
}
