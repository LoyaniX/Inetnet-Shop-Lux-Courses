package com.loyanix.services.impl;

import com.loyanix.dao.ClientDao;
import com.loyanix.dao.impl.ClientDaoImpl;
import com.loyanix.domain.Client;
import com.loyanix.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao = new ClientDaoImpl();

    @Override
    public void create(String name, String surname, String phone) {

    }

    @Override
    public void getById(Long id) {

    }

    @Override
    public void update(Long id, String name, String surname, String phone) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Client> findAll() {
        return null;
    }
}
