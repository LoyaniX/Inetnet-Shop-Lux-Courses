package com.loyanix.view;

import com.loyanix.services.ClientService;
import com.loyanix.services.OrderService;
import com.loyanix.services.ProductService;
import com.loyanix.services.dto.ClientDto;
import com.loyanix.services.dto.OrderDto;
import com.loyanix.services.dto.ProductDto;
import com.loyanix.services.impl.ClientServiceImpl;
import com.loyanix.services.impl.OrderServiceImpl;
import com.loyanix.services.impl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AdminMenu {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private final ClientService clientService = new ClientServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    public void show() throws IOException {

        addExampleData();

        while (true) {

            showMenu();

            switch (bufferedReader.readLine()) {
                case "1":
                    showClientMenu();
                    break;
                case "2":
                    showProductMenu();
                    break;
                case "3":
                    showOrderMenu();
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

    private void addExampleData() {
        productService.create(new ProductDto("Tesla", new BigDecimal(123456789), "unisex", "Model X", 12));
        productService.create(new ProductDto("Tesla", new BigDecimal(2412312), "male", "Model S", 123));
        productService.create(new ProductDto("Iphone", new BigDecimal(1000), "unisex", "Xs max", 56));
        productService.create(new ProductDto("T-short", new BigDecimal(12), "female", "xs", 120));

        clientService.create(new ClientDto("Max", "Loyan", 21, "loyanmaksim@gmail.com", ""));
        clientService.create(new ClientDto("Maksim", "Loyan", 25, "", ""));
        clientService.create(new ClientDto("John", "Shelvy", 12, "", "0995696886"));
        clientService.create(new ClientDto("Marina", "Kayl", 65, "loyanmaksim@gmail.com", "0995696886"));

        List<ProductDto> exampleProducts = new ArrayList<>(productService.findAll());
        for (long i = 1; i < 5; i++) {
            orderService.create(new OrderDto(clientService.getById(i), exampleProducts, null, null));
        }
    }

    private void deleteProduct() throws IOException {
        System.out.println("Enter ID to delete:");
        Long id = Long.parseLong(bufferedReader.readLine());
        productService.delete(id);
    }

    private void updateProduct() throws IOException {
        System.out.println("Enter ID to update:");
        Long id = Long.parseLong(bufferedReader.readLine());
        ProductDto productToUpdate = createProduct();
        productService.update(id, productToUpdate);
    }

    private ProductDto showProduct() throws IOException {
        System.out.println("Enter ID to show:");
        Long id = Long.parseLong(bufferedReader.readLine());
        return productService.getById(id);
    }

    private ProductDto createProduct() throws IOException {
        System.out.println("Input Name of Product");
        String name = bufferedReader.readLine();
        System.out.println("Input Price of Product");
        Long priceLong = Long.parseLong(bufferedReader.readLine());
        BigDecimal price = new BigDecimal(priceLong);
        System.out.println("Input Gender of Product");
        String gender = bufferedReader.readLine();
        System.out.println("Input Size of Product");
        String size = bufferedReader.readLine();
        System.out.println("Input Quantity of Product");
        int quantity = Integer.parseInt(bufferedReader.readLine());
        return new ProductDto(name, price, gender, size, quantity);
    }

    private ClientDto createClient() throws IOException {
        System.out.println("Input Name of Client");
        String name = bufferedReader.readLine();
        System.out.println("Input SurName of Client");
        String surName = bufferedReader.readLine();
        System.out.println("Input Age of Client");
        Integer age = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Input Email of Client");
        String email = bufferedReader.readLine();
        System.out.println("Input Phone number of Client");
        String phone = bufferedReader.readLine();
        return new ClientDto(name, surName, age, email, phone);
    }

    private ClientDto showClient() throws IOException {
        System.out.println("Enter ID to show:");
        Long id = Long.parseLong(bufferedReader.readLine());
        return clientService.getById(id);
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

    private OrderDto createOrder() {
        return null;
    }

    private OrderDto showOrder() throws IOException {
        System.out.println("Enter ID to show:");
        Long id = Long.parseLong(bufferedReader.readLine());
        return orderService.getById(id);
    }

    private void deleteOrder() {
    }

    private void updateOrder() {
    }

    private void getListOfEntities(String entity) {
        switch (entity.toLowerCase()) {
            case "products":
                for (ProductDto productDto : productService.findAll()) {
                    System.out.println(productDto);
                }
                break;
            case "clients":
                for (ClientDto clientDtoDto : clientService.findAll()) {
                    System.out.println(clientDtoDto);
                }
                break;
            case "orders":
                for (OrderDto orderDto : orderService.findAll()) {
                    System.out.println(orderDto);
                }
                break;
            default:
                System.out.println("Wrong Entity");
        }
    }

    private void showMenu() {
        System.out.println("1. Client");
        System.out.println("2. Product");
        System.out.println("3. Order");
        System.out.println("4. Return");
        System.out.println("0. Exit");
    }

    private void showClientMenu() throws IOException {
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
                getListOfEntities("clients");
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

    private void showProductMenu() throws IOException {
        System.out.println("1. Add Product");
        System.out.println("2. Modify Product");
        System.out.println("3. Remove Product");
        System.out.println("4. Show Product");
        System.out.println("5. List of Products");
        System.out.println("6. Return");
        System.out.println("0. Exit");
        switch (bufferedReader.readLine()) {
            case "1":
                productService.create(createProduct());
                break;
            case "2":
                updateProduct();
                break;
            case "3":
                deleteProduct();
                break;
            case "4":
                System.out.println(showProduct());
                break;
            case "5":
                getListOfEntities("products");
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

    private void showOrderMenu() throws IOException {
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
                System.out.println(showOrder());
                break;
            case "5":
                getListOfEntities("orders");
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
