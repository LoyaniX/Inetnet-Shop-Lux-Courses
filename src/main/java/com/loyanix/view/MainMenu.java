package com.loyanix.view;

import com.loyanix.services.ClientService;
import com.loyanix.services.OrderService;
import com.loyanix.services.ProductService;
import com.loyanix.services.authorization.AdminAuth;
import com.loyanix.services.authorization.impl.AdminAuthImpl;
import com.loyanix.services.impl.ClientServiceImpl;
import com.loyanix.services.impl.OrderServiceImpl;
import com.loyanix.services.impl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenu {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private ClientService clientService = new ClientServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    private final AdminMenu adminMenu = new AdminMenu(clientService, productService, orderService);
    private final ClientMenu clientMenu = new ClientMenu(clientService, productService, orderService);
    private final AdminAuth adminAuth = new AdminAuthImpl();

    public void showMenu() throws IOException {

        boolean isRuning = true;

        while (isRuning) {

            System.out.println("1. Admin");
            System.out.println("2. Client");
            System.out.println("0. Exit");

            switch (bufferedReader.readLine()) {

                case "1":
                    System.out.println("Enter admin password to go to admin menu:");
                    if (adminAuth.authenticate(bufferedReader.readLine())){
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
