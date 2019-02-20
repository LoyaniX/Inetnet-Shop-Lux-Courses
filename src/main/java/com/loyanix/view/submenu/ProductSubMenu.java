package com.loyanix.view.submenu;

import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.ProductService;
import com.loyanix.services.dto.ProductDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

public class ProductSubMenu {

    private BufferedReader bufferedReader;
    private ProductService productService;

    public ProductSubMenu(BufferedReader bufferedReader, ProductService productService) {
        this.bufferedReader = bufferedReader;
        this.productService = productService;
    }

    private void deleteProduct() throws IOException {
        try {
            System.out.println("Enter ID to delete:");
            Long id = Long.parseLong(bufferedReader.readLine());
            productService.delete(id);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct() throws IOException {
        System.out.println("Enter ID to update:");
        Long id = Long.parseLong(bufferedReader.readLine());
        ProductDto productToUpdate = createProduct();
        productService.update(id, productToUpdate);
    }

    private ProductDto showProduct() throws IOException, BusinessException {
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


    private void getListOfProducts() {
        for (ProductDto productDto : productService.findAll()) {
            System.out.println(productDto);
        }
    }

    public void showProductMenuForAdmin() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Add Product");
            System.out.println("2. Modify Product");
            System.out.println("3. Remove Product");
            System.out.println("4. Show Product");
            System.out.println("5. List of Products");
            System.out.println("6. Return");
            System.out.println("0. Exit");
            switch (bufferedReader.readLine()) {
                case "1":
                    createProduct();
                    break;
                case "2":
                    updateProduct();
                    break;
                case "3":
                    deleteProduct();
                    break;
                case "4":
                    try {
                        System.out.println(showProduct());
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    getListOfProducts();
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

    public void showProductMenuForClient() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Show Product");
            System.out.println("2. List of Products");
            System.out.println("R. Return");
            System.out.println("E. Exit");
            switch (bufferedReader.readLine()) {
                case "1":
                    try {
                        System.out.println(showProduct());
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    getListOfProducts();
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
