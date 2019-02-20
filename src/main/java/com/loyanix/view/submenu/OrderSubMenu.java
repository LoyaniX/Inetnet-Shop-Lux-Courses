package com.loyanix.view.submenu;

import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.OrderService;
import com.loyanix.services.dto.OrderDto;

import java.io.BufferedReader;
import java.io.IOException;

public class OrderSubMenu {

    private BufferedReader bufferedReader;
    private OrderService orderService;

    public OrderSubMenu(BufferedReader bufferedReader, OrderService orderService) {
        this.bufferedReader = bufferedReader;
        this.orderService = orderService;
    }

    private static OrderDto createOrder() {
        return null;
    }

    private OrderDto showOrder() throws IOException, BusinessException {
        System.out.println("Enter ID to show:");
        long id = Long.parseLong(bufferedReader.readLine());
        return orderService.getById(id);
    }

    private void deleteOrder() throws IOException {
        System.out.println("Enter ID to delete:");
        long id = Long.parseLong(bufferedReader.readLine());
        orderService.delete(id);
    }

    private void updateOrder() {
    }

    private void getListOfOrders() {
        for (OrderDto orderDto : orderService.findAll()) {
            System.out.println(orderDto);
        }
    }

    public void showOrderMenuForAdmin() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Add Order");
            System.out.println("2. Modify Order");
            System.out.println("3. Remove Order");
            System.out.println("4. Show Order");
            System.out.println("5. List of Orders");
            System.out.println("6. Return");
            System.out.println("0. Exit");
            switch (bufferedReader.readLine()) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    deleteOrder();
                    break;
                case "4":
                    try {
                        System.out.println(showOrder());
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    getListOfOrders();
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

    public void showOrderMenuForClient() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Add Order");
            System.out.println("2. Modify Order");
            System.out.println("3. Remove Order");
            System.out.println("4. Show Order");
            System.out.println("R. Return");
            System.out.println("E. Exit");
            switch (bufferedReader.readLine()) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    deleteOrder();
                    break;
                case "4":
                    try {
                        System.out.println(showOrder());
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }
                    break;
                case "R":
                    return;
                case "E":
                    System.exit(0);
                default:
                    System.out.println("Wrong symbol");
                    break;
            }
        }
    }
}
