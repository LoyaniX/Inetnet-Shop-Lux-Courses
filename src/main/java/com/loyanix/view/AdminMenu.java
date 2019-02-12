package com.loyanix.view;

import com.loyanix.domain.Client;
import com.loyanix.services.ClientService;
import com.loyanix.services.ProductService;
import com.loyanix.services.dto.ClientDto;
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
                case "6":
                    productService.create(createProduct());
                    break;
                case "7":
                    updateProduct();
                    break;
                case "8":
                    deleteProduct();
                    break;
                case "9":
                    System.out.println(showProduct());
                    break;
                case "10":
                    getListOfEntities("products");
                    break;
                case "11":
                    return;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Wrong symbol");
                    break;
            }
        }
    }

    private void addExempleData(){
        productService.create(new ProductDto("Tesla", new BigDecimal(123456789), "unisex", "Model X", 12));
        productService.create(new ProductDto("Tesla", new BigDecimal(2412312), "male", "Model S", 123));
        productService.create(new ProductDto("Iphone", new BigDecimal(1000), "unisex", "Xs max", 56));
        productService.create(new ProductDto("T-short", new BigDecimal(12), "female", "xs", 120));
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

    //    private ClientDto showClient() throws IOException {
//        System.out.println("Enter ID to show:");
//        Long id = Long.parseLong(bufferedReader.readLine());
//        return clientService.getById(id);
//    }

    private void getListOfEntities(String entity) {
        switch (entity.toLowerCase()) {
            case "products":
                for (ProductDto productDto : productService.findAll()) {
                    System.out.println(productDto);
                }
                break;
            default:
                System.out.println("Wrong Entity");
        }
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
        System.out.println("4. Show Client");
        System.out.println("5. List of Clients");
        System.out.println("6. Add Product");
        System.out.println("7. Modify Product");
        System.out.println("8. Remove Product");
        System.out.println("9. Show Product");
        System.out.println("10. List of Product");
        System.out.println("11. Return");
        System.out.println("0. Exit");
    }
}
