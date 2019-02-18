package com.loyanix.view;

import com.loyanix.view.submenu.ClientSubMenu;
import com.loyanix.view.submenu.OrderSubMenu;
import com.loyanix.view.submenu.ProductSubMenu;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientMenu {

    private BufferedReader bufferedReader;

    private ClientSubMenu clientSubMenu;
    private OrderSubMenu orderSubMenu;
    private ProductSubMenu productSubMenu;

    public ClientMenu(BufferedReader bufferedReader, ClientSubMenu clientSubMenu, OrderSubMenu orderSubMenu, ProductSubMenu productSubMenu) {
        this.bufferedReader = bufferedReader;
        this.clientSubMenu = clientSubMenu;
        this.orderSubMenu = orderSubMenu;
        this.productSubMenu = productSubMenu;
    }

    public void show() throws IOException {

        while (true) {

            showMenu();

            switch (bufferedReader.readLine()) {
                case "1":
                    clientSubMenu.showClientMenuForAdmin();
                    break;
                case "2":
                    orderSubMenu.showOrderMenuForClient();
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
