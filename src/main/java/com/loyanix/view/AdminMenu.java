package com.loyanix.view;

import com.loyanix.view.submenu.ClientSubMenu;
import com.loyanix.view.submenu.OrderSubMenu;
import com.loyanix.view.submenu.ProductSubMenu;

import java.io.BufferedReader;
import java.io.IOException;

public class AdminMenu {

    private BufferedReader bufferedReader;

    private ClientSubMenu clientSubMenu;
    private OrderSubMenu orderSubMenu;
    private ProductSubMenu productSubMenu;

    public AdminMenu(BufferedReader bufferedReader, ClientSubMenu clientSubMenu, OrderSubMenu orderSubMenu, ProductSubMenu productSubMenu) {
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
                    productSubMenu.showProductMenuForAdmin();
                    break;
                case "3":
                    orderSubMenu.showOrderMenuForAdmin();
                    break;
                case "4":
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
        System.out.println("1. Client");
        System.out.println("2. Product");
        System.out.println("3. Order");
        System.out.println("4. Return");
        System.out.println("0. Exit");
    }

}
