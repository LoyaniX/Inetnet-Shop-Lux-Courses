package com.loyanix.services.impl;

import com.loyanix.dao.ClientDao;
import com.loyanix.dao.impl.ClientDaoImpl;
import com.loyanix.domain.Client;
import com.loyanix.services.ClientService;
import com.loyanix.services.converter.ClientConverter;
import com.loyanix.services.converter.impl.ClientConverterImpl;
import com.loyanix.services.dto.ClientDto;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao = new ClientDaoImpl();
    private ClientConverter clientConverter = new ClientConverterImpl();

    @Override
    public void create(ClientDto clientDto) {
        clientDao.create(clientConverter.toEntity(clientDto));
    }

    @Override
    public ClientDto getById(Long id) {
        try {
            return clientConverter.toDto(clientDao.getById(id));
        } catch (NullPointerException e) {
            System.out.println("Client with id " + id + " is absent");
            e.getMessage();
            return null;
        }
    }

    @Override
    public void update(Long id, ClientDto clientDto) {
        clientDao.update(id, clientConverter.toEntity(clientDto));
    }

    @Override
    public void delete(Long id) {
        try {
            clientDao.delete(id);
        } catch (NullPointerException e) {
            System.out.println("Client with id " + id + " is absent");
            e.getMessage();
        }
    }

    @Override
    public List<ClientDto> findAll() {
        List<Client> clientList = clientDao.findAll();
        List<ClientDto> clientDtos = new ArrayList<>();
        for (Client client : clientList) {
            clientDtos.add(clientConverter.toDto(client));
        }
        return clientDtos;
    }
}
