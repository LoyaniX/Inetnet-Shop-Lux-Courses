package com.loyanix.view.util;

import com.loyanix.services.OrderService;
import com.loyanix.services.dto.OrderDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderUtil {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static OrderDto createOrder() {
        return null;
    }

    private static OrderDto showOrder(OrderService orderService) throws IOException {
        System.out.println("Enter ID to show:");
        Long id = Long.parseLong(bufferedReader.readLine());
        return orderService.getById(id);
    }

    private static void deleteOrder() {

    }

    private static void updateOrder() {
    }

    private static void getListOfOrders(OrderService orderService) {
        for (OrderDto orderDto : orderService.findAll()) {
            System.out.println(orderDto);
        }
    }

    public static void showOrderMenu(OrderService orderService) throws IOException {
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
                break;
            case "4":
                System.out.println(showOrder(orderService));
                break;
            case "5":
                getListOfOrders(orderService);
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
