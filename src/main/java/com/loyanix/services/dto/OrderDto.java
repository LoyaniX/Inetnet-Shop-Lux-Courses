package com.loyanix.services.dto;

import java.util.List;

public class OrderDto {

    private long id;
    private ClientDto client;
    private List<ProductDto> products;

    public OrderDto() {
    }

    public OrderDto(ClientDto client, List<ProductDto> products) {
        this.client = client;
        this.products = products;
    }

    public OrderDto(long id, ClientDto client, List<ProductDto> products) {
        this.id = id;
        this.client = client;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", client=" + client +
                ", products=" + products +
                '}';
    }
}
