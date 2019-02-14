package com.loyanix.view;

import com.loyanix.dao.DataSetupUitl;
import com.loyanix.services.ClientService;
import com.loyanix.services.OrderService;
import com.loyanix.services.ProductService;
import com.loyanix.services.impl.ClientServiceImpl;
import com.loyanix.services.impl.OrderServiceImpl;
import com.loyanix.services.impl.ProductServiceImpl;
import com.loyanix.view.util.ClientUtil;
import com.loyanix.view.util.OrderUtil;
import com.loyanix.view.util.ProductUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMenu {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private ClientService clientService;
    private ProductService productService;
    private OrderService orderService;

    public ClientMenu(ClientService clientService, ProductService productService, OrderService orderService) {
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
    }

    public void show() throws IOException {

        DataSetupUitl.addExampleData(clientService, productService, orderService);

        while (true) {

            showMenu();

            switch (bufferedReader.readLine()) {
                case "1":
                    ProductUtil.showProductMenu(productService);
                    break;
                case "2":
                    OrderUtil.showOrderMenu(orderService);
                    break;
                case "3":
                    return;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Wrong symbol");
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println("1. Product");
        System.out.println("2. Order");
        System.out.println("3. Return");
        System.out.println("0. Exit");
    }
}
