package com.loyanix.services.converter;

import com.loyanix.domain.Client;
import com.loyanix.services.dto.ClientDto;

public interface ClientConverter {

    Client toEntity(ClientDto clientDto);

    ClientDto toDto(Client client);
}
