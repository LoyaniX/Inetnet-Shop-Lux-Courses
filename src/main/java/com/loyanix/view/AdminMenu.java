package com.loyanix.view;

import com.loyanix.services.ClientService;
import com.loyanix.services.impl.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminMenu {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final ClientService clientService = new ClientServiceImpl();

    public void show() throws IOException {

        while (true) {

            showMenu();

            switch (bufferedReader.readLine()) {
                case "1":
                    createClient();
                    break;
                case "2":
                    System.out.println("Modify client");
                    break;
                case "3":
                    System.out.println("Add client");
                    break;
                case "0":
                    return;
                case "9":
                    //TODO: exit from app
                    return;
                default:
                    System.out.println("Wrong symbol");
                    break;
            }
        }
    }

    private void createClient() throws IOException {
        System.out.println("Input Name of Client");
        String name = bufferedReader.readLine();
        System.out.println("Input SurName of Client");
        String surName = bufferedReader.readLine();
        System.out.println("Input Phone number of Client");
        String phone = bufferedReader.readLine();
        clientService.createClient(name, surName, phone);
    }

    private void showMenu() {
        System.out.println("1. Add Client");
        System.out.println("2. Modify Client");
        System.out.println("3. Remove Client");
        System.out.println("4. List of Clients");
        System.out.println("9. Return");
        System.out.println("0. Exit");
    }
}
