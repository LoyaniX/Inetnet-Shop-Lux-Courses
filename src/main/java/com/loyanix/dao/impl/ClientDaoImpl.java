package com.loyanix.dao.impl;

import com.loyanix.dao.ClientDao;
import com.loyanix.domain.Client;

import java.util.HashMap;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {

    private Map<Long, Client> clientMap = new HashMap<>();

    @Override
    public void create(Client client) {
        clientMap.put(client.getId(), client);
    }

    @Override
    public void getById(Long id) {
        clientMap.get(id);
    }

    @Override
    public void update(Long id, Client client) {
        clientMap.put(id, client);
    }

    @Override
    public void delete(Long id) {
        clientMap.remove(id);
    }

    @Override
    public Map<Long, Client> findAll() {
        return clientMap;
    }
}
