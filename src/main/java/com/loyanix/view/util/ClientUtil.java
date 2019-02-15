package com.loyanix.view.util;

import com.loyanix.services.ClientService;
import com.loyanix.services.dto.ClientDto;
import com.loyanix.services.impl.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientUtil {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static ClientDto createClient() throws IOException {
        System.out.println("Input Name of Client");
        String name = bufferedReader.readLine();
        System.out.println("Input SurName of Client");
        String surName = bufferedReader.readLine();
        System.out.println("Input Age of Client");
        Integer age = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Input Email of Client");
        String email = bufferedReader.readLine();
        System.out.println("Input Phone number of Client");
        String phone = bufferedReader.readLine();
        return new ClientDto(name, surName, age, email, phone);
    }

    private static ClientDto showClient(ClientService clientService) throws IOException {
        System.out.println("Enter ID to show:");
        Long id = Long.parseLong(bufferedReader.readLine());
        return clientService.getById(id);
    }

    private static void deleteClient(ClientService clientService) throws IOException {
        System.out.println("Enter ID to delete:");
        Long id = Long.parseLong(bufferedReader.readLine());
        clientService.delete(id);
    }

    private static void updateClient(ClientService clientService) throws IOException {
        System.out.println("Enter ID to update:");
        Long id = Long.parseLong(bufferedReader.readLine());
        ClientDto clientToUpdate = createClient();
        clientService.update(id, clientToUpdate);
    }

    private static void getListOfClients(ClientService clientService) {
        for (ClientDto clientDtoDto : clientService.findAll()) {
            System.out.println(clientDtoDto);
        }
    }

    public static void showClientMenu(ClientService clientService) throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Add Client");
            System.out.println("2. Modify Client");
            System.out.println("3. Remove Client");
            System.out.println("4. Show Client");
            System.out.println("5. List of Clients");
            System.out.println("6. Return");
            System.out.println("0. Exit");
            switch (bufferedReader.readLine()) {
                case "1":
                    clientService.create(createClient());
                    break;
                case "2":
                    updateClient(clientService);
                    break;
                case "3":
                    deleteClient(clientService);
                    break;
                case "4":
                    System.out.println(showClient(clientService));
                    break;
                case "5":
                    getListOfClients(clientService);
                    break;
                case "6":
                    return;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Wrong symbol");
                    break;
            }
        }
    }
}
