package com.loyanix.dao.impl;

import com.loyanix.dao.ClientDao;
import com.loyanix.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {

    private static ClientDaoImpl instance = null;

    private ClientDaoImpl() {}

    public static synchronized ClientDaoImpl getInstance() {
        if (instance == null)
            instance = new ClientDaoImpl();
        return instance;
    }

    private Map<Long, Client> clientMap = new HashMap<>();
    private long id = 1L;

    @Override
    public void create(Client client) {
        client.setId(id++);
        clientMap.put(client.getId(), client);
    }

    @Override
    public Client getById(Long id) {
        return clientMap.get(id);
    }

    @Override
    public void update(Long id, Client client) {
        client.setId(id);
        clientMap.put(client.getId(), client);
    }

    @Override
    public void delete(Long id) {
        clientMap.remove(id);
    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(clientMap.values());
    }
}
