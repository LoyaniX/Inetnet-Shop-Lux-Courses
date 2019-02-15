package com.loyanix.services.converter.impl;

import com.loyanix.domain.Client;
import com.loyanix.services.converter.ClientConverter;
import com.loyanix.services.dto.ClientDto;

public class ClientConverterImpl implements ClientConverter {
    @Override
    public Client toEntity(ClientDto clientDto) {
        return new Client(clientDto.getId(), clientDto.getName(), clientDto.getSurname(), clientDto.getAge(), clientDto.getEmail(), clientDto.getPhone());
    }

    @Override
    public ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), client.getName(), client.getSurname(), client.getAge(), client.getEmail(), client.getPhone());
    }
}
