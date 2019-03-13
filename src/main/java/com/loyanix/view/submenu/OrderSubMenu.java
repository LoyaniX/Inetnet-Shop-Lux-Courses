package com.loyanix.view.submenu;

import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.OrderService;
import com.loyanix.services.dto.ClientDto;
import com.loyanix.services.dto.OrderDto;
import com.loyanix.services.dto.ProductDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderSubMenu {

    private BufferedReader bufferedReader;
    private OrderService orderService;
    private ClientSubMenu clientSubMenu;
    private ProductSubMenu productSubMenu;

    public OrderSubMenu(BufferedReader bufferedReader, OrderService orderService, ClientSubMenu clientSubMenu, ProductSubMenu productSubMenu) {
        this.bufferedReader = bufferedReader;
        this.orderService = orderService;
        this.clientSubMenu = clientSubMenu;
        this.productSubMenu = productSubMenu;
    }

    private void createOrder() throws IOException, BusinessException {
        System.out.println("List of clients");
        clientSubMenu.getListOfClients();
        System.out.println("Enter client id:");
        long clientId = Long.parseLong(bufferedReader.readLine());
        ClientDto clientDto = clientSubMenu.showClient(clientId);
        System.out.println("List of products");
        productSubMenu.getListOfProducts();
        System.out.println("Enter id's of products:");
        String products = bufferedReader.readLine();
        String[] ids   = products.split(" ");
        List<ProductDto> productDtos = new ArrayList<>();
        for (String id : ids) {
            productDtos.add(productSubMenu.showProduct(Long.parseLong(id)));
        }
        orderService.create(new OrderDto(clientDto, productDtos, null, null));
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

    private void updateOrder() throws IOException, BusinessException {
        System.out.println("List of orders");
        orderService.findAll();
        System.out.println("Enter order id:");
        long orderId = Long.parseLong(bufferedReader.readLine());
        OrderDto orderDto = orderService.getById(orderId);
        System.out.println(orderDto);
        System.out.println("List of products");
        productSubMenu.getListOfProducts();
        System.out.println("Enter id's of products:");
        String products = bufferedReader.readLine();
        String[] ids   = products.split(" ");
        List<ProductDto> productDtos = new ArrayList<>();
        for (String id : ids) {
            productDtos.add(productSubMenu.showProduct(Long.parseLong(id)));
        }
        orderService.update(new OrderDto(orderDto.getClient(), productDtos, null, null));
    }

    private List<OrderDto> getOrdersOfClient(long clientId) {
        return orderService.findAllByClient(clientId);
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
            System.out.println("6. Show Orders of client");
            System.out.println("R. Return");
            System.out.println("E. Exit");
            switch (bufferedReader.readLine()) {
                case "1":
                    try {
                        createOrder();
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }
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
                    System.out.println("Enter client id:");
                    long id = Long.parseLong(bufferedReader.readLine());
                    for (OrderDto orderDto : getOrdersOfClient(id)) {
                        System.out.println(orderDto);
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

    public void showOrderMenuForClient() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Add Order");
            System.out.println("2. Modify Order");
            System.out.println("3. Remove Order");
            System.out.println("4. Show Order");
            System.out.println("5. Show Order of client");
            System.out.println("R. Return");
            System.out.println("E. Exit");
            switch (bufferedReader.readLine()) {
                case "1":
                    try {
                        createOrder();
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }
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
                    System.out.println("Enter client id:");
                    long id = Long.parseLong(bufferedReader.readLine());
                    for (OrderDto orderDto : getOrdersOfClient(id)) {
                        System.out.println(orderDto);
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
