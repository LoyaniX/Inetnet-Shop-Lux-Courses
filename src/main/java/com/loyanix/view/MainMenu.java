package com.loyanix.view;

import com.loyanix.services.authorization.AdminAuth;

import java.io.BufferedReader;
import java.io.IOException;

public class MainMenu {

    private final BufferedReader bufferedReader;

    private final AdminMenu adminMenu;
    private final ClientMenu clientMenu;

    private final AdminAuth adminAuth;

    public MainMenu(BufferedReader bufferedReader, AdminMenu adminMenu, ClientMenu clientMenu, AdminAuth adminAuth) {
        this.bufferedReader = bufferedReader;
        this.adminMenu = adminMenu;
        this.clientMenu = clientMenu;
        this.adminAuth = adminAuth;
    }

    public void showMenu() throws IOException {

        boolean isRuning = true;

        while (isRuning) {

            System.out.println("1. Admin");
            System.out.println("2. Client");
            System.out.println("0. Exit");

            switch (bufferedReader.readLine()) {

                case "1":
                    System.out.println("Enter admin password to go to admin menu:");
                    if (adminAuth.authenticate(bufferedReader.readLine())) {
                        adminMenu.show();
                    } else {
                        System.out.println("\nWrong password\n");
                    }
                    break;
                case "2":
                    clientMenu.show();
                    break;
                case "0":
                    isRuning = false;
                    break;
                default:
                    System.out.println("Wrong symbol");
                    break;
            }
        }
    }
}
