package com.loyanix.view;

import com.loyanix.services.ClientService;
import com.loyanix.services.ProductService;
import com.loyanix.services.dto.ProductDto;
import com.loyanix.services.impl.ClientServiceImpl;
import com.loyanix.services.impl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

public class AdminMenu {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final ClientService clientService = new ClientServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

    public void show() throws IOException {

        while (true) {

            showMenu();

            switch (bufferedReader.readLine()) {
                case "1":
                    createClient();
                    break;
                case "5":
                    createProduct();
                    break;
                case "8":
                    getListOfEntities("products");
                    break;
                case "0":
                    return;
                case "9":
                    System.exit(0);
                default:
                    System.out.println("Wrong symbol");
                    break;
            }
        }
    }

    private void getListOfEntities(String entity){
        switch (entity.toLowerCase()){
            case "products":
                for (ProductDto productDto : productService.findAll()) {
                    System.out.println(productDto);
                }
                break;
            default:
                System.out.println("Wrong Entity");
        }
    }
    private void createProduct() throws IOException {
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
        ProductDto productDto = new ProductDto(name, price, gender, size, quantity);
        productService.create(productDto);
    }

    private void createClient() throws IOException {
        System.out.println("Input Name of Client");
        String name = bufferedReader.readLine();
        System.out.println("Input SurName of Client");
        String surName = bufferedReader.readLine();
        System.out.println("Input Phone number of Client");
        String phone = bufferedReader.readLine();
    //    clientService.createClient(name, surName, phone);
    }

    private void showMenu() {
        System.out.println("1. Add Client");
        System.out.println("2. Modify Client");
        System.out.println("3. Remove Client");
        System.out.println("4. List of Clients");
        System.out.println("5. Add Product");
        System.out.println("6. Modify Product");
        System.out.println("7. Remove Product");
        System.out.println("8. List of Product");
        System.out.println("9. Return");
        System.out.println("0. Exit");
    }
}
