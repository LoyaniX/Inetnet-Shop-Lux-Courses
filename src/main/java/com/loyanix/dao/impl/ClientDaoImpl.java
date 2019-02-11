package com.loyanix.dao.impl;

import com.loyanix.dao.ClientDao;
import com.loyanix.domain.Client;

public class ClientDaoImpl implements ClientDao {
    @Override
    public boolean saveClient(Client client) {
        System.out.println("Saving . . . .  Please wait");
        return true;
    }
}
