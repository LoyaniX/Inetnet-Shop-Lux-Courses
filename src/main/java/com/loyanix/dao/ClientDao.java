package com.loyanix.dao;

import com.loyanix.domain.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao {

    void create(Client client);

    Client getById(Long id);

    void update(Client client);

    void delete(Long id);

    List<Client> findAll();

}
