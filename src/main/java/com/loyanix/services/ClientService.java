package com.loyanix.services;

import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.dto.ClientDto;

import java.util.List;

public interface ClientService {

    void create(ClientDto clientDto);

    ClientDto getById(Long id) throws BusinessException;

    void update(ClientDto clientDto) throws BusinessException;

    void delete(Long id);

    List<ClientDto> findAll();
}
