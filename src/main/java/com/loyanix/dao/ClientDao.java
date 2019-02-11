package com.loyanix.dao;

import com.loyanix.domain.Client;

import java.util.Map;

public interface ClientDao {

    void create(Client client);
    void getById(Long id);
    void update(Long id, Client client);
    void delete(Long id);
    Map<Long, Client> findAll();

}
