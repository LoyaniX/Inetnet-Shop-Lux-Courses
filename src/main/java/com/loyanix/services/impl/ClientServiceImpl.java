package com.loyanix.services.impl;

import com.loyanix.dao.ClientDao;
import com.loyanix.domain.Client;
import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.ClientService;
import com.loyanix.services.converter.ClientConverter;
import com.loyanix.services.dto.ClientDto;
import com.loyanix.validator.ValidationService;

import java.util.List;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;
    private ClientConverter clientConverter;
    private ValidationService validationService;

    public ClientServiceImpl(ClientDao clientDao, ClientConverter clientConverter, ValidationService validationService) {
        this.clientDao = clientDao;
        this.clientConverter = clientConverter;
        this.validationService = validationService;
    }

    @Override
    public void create(ClientDto clientDto) {
        try {
            validationService.validateAge(clientDto.getAge());
            validationService.validateEmail(clientDto.getEmail());
            validationService.validatePhone(this, clientDto.getPhone());
            clientDao.create(clientConverter.toEntity(clientDto));
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClientDto getById(Long id) throws BusinessException {
        validationService.validateId(this, id);
        return clientConverter.toDto(clientDao.getById(id));
    }

    @Override
    public void update(Long id, ClientDto clientDto) throws BusinessException {
        validationService.validateAge(clientDto.getAge());
        validationService.validateEmail(clientDto.getEmail());
        validationService.validatePhone(this, clientDto.getPhone());
        clientDao.update(id, clientConverter.toEntity(clientDto));
    }

    @Override
    public void delete(Long id) {
        try {
            validationService.validateId(this, id);
            clientDao.delete(id);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClientDto> findAll() {
        List<Client> clientList = clientDao.findAll();
        return clientList.stream()
                .map(client -> clientConverter.toDto(client))
                .collect(Collectors.toList());
    }
}
