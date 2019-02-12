package com.loyanix.services;

import com.loyanix.domain.Client;
import com.loyanix.services.dto.ClientDto;

import java.util.List;

public interface ClientService {

    /**
     * Add JAVA_DOC
     */
    void create(ClientDto clientDto);

    ClientDto getById(Long id);

    void update(Long id, ClientDto clientDto);

    void delete(Long id);

    List<ClientDto> findAll();
}
