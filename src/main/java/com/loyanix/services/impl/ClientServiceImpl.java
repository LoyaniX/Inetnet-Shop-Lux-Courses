package com.loyanix.services.impl;

import com.loyanix.dao.ClientDao;
import com.loyanix.dao.impl.ClientDaoImpl;
import com.loyanix.domain.Client;
import com.loyanix.services.ClientService;

public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao = new ClientDaoImpl();
    @Override
    public void createClient(String name, String surname, String phone) {

        Client client = new Client(name, surname, phone);
        boolean isSaveClient = clientDao.saveClient(client);
        if (isSaveClient) {
            System.out.println("Client saved:" + client);
        }
    }

    @Override
    public void deleteClient() {

    }
}
