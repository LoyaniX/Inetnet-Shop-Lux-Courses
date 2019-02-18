package com.loyanix.view.submenu;

import com.loyanix.services.ClientService;
import com.loyanix.services.dto.ClientDto;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientSubMenu {

    private BufferedReader bufferedReader;
    private ClientService clientService;

    public ClientSubMenu(BufferedReader bufferedReader, ClientService clientService) {
        this.bufferedReader = bufferedReader;
        this.clientService = clientService;
    }

    private ClientDto createClient() throws IOException {
        System.out.println("Input Name of Client");
        String name = bufferedReader.readLine();
        System.out.println("Input SurName of Client");
        String surName = bufferedReader.readLine();
        System.out.println("Input Age of Client");
        int age = readInteger();
        System.out.println("Input Email of Client");
        String email = bufferedReader.readLine();
        System.out.println("Input Phone number of Client");
        String phone = bufferedReader.readLine();
        return new ClientDto(name, surName, age, email, phone);
    }

    private void deleteClient() throws IOException {
        System.out.println("Enter ID to delete:");
        Long id = Long.parseLong(bufferedReader.readLine());
        clientService.delete(id);
    }

    private void updateClient() throws IOException {
        System.out.println("Enter ID to update:");
        Long id = Long.parseLong(bufferedReader.readLine());
        ClientDto clientToUpdate = createClient();
        clientService.update(id, clientToUpdate);
    }

    private ClientDto showClient() throws IOException {
        System.out.println("Enter ID to show:");
        Long id = Long.parseLong(bufferedReader.readLine());
        return clientService.getById(id);
    }

    private void getListOfClients() {
        for (ClientDto clientDtoDto : clientService.findAll()) {
            System.out.println(clientDtoDto);
        }
    }

    public void showClientMenuForAdmin() throws IOException {
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
                    updateClient();
                    break;
                case "3":
                    deleteClient();
                    break;
                case "4":
                    System.out.println(showClient());
                    break;
                case "5":
                    getListOfClients();
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

    public void showClientMenuForClient() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Add Client");
            System.out.println("2. Modify Client");
            System.out.println("3. Remove Client");
            System.out.println("4. Show Client");
            System.out.println("6. Return");
            System.out.println("0. Exit");
            switch (bufferedReader.readLine()) {
                case "1":
                    clientService.create(createClient());
                    break;
                case "2":
                    updateClient();
                    break;
                case "3":
                    deleteClient();
                    break;
                case "4":
                    System.out.println(showClient());
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

    private int readInteger() {
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Input number:");
            return readInteger();
        }
    }



}
