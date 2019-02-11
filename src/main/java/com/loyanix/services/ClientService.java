package com.loyanix.services;

import com.loyanix.domain.Client;

import java.util.Map;

public interface ClientService {

    /**
     * Add JAVA_DOC
     */
    void createClient(String name, String surname, String phone);

    /**
     * Add JAVA_DOC
     */
    void create(String name, String surname, String phone);
    void getById(Long id);
    void update(Long id, String name, String surname, String phone);
    void delete(Long id);
    Map<Long, Client> findAll();
}
