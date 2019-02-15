package com.loyanix.view.util;

import com.loyanix.services.ProductService;
import com.loyanix.services.dto.ProductDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class ProductUtil {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static void deleteProduct(ProductService productService) throws IOException {
        System.out.println("Enter ID to delete:");
        Long id = Long.parseLong(bufferedReader.readLine());
        productService.delete(id);
    }

    private static void updateProduct(ProductService productService) throws IOException {
        System.out.println("Enter ID to update:");
        Long id = Long.parseLong(bufferedReader.readLine());
        ProductDto productToUpdate = createProduct(productService);
        productService.update(id, productToUpdate);
    }

    private static ProductDto showProduct(ProductService productService) throws IOException {
        System.out.println("Enter ID to show:");
        Long id = Long.parseLong(bufferedReader.readLine());
        return productService.getById(id);
    }

    private static ProductDto createProduct(ProductService productService) throws IOException {
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


    private static void getListOfProducts(ProductService productService) {
        for (ProductDto productDto : productService.findAll()) {
            System.out.println(productDto);
        }
    }

    public static void showProductMenu(ProductService productService) throws IOException {
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
                    createProduct(productService);
                    break;
                case "2":
                    updateProduct(productService);
                    break;
                case "3":
                    deleteProduct(productService);
                    break;
                case "4":
                    System.out.println(showProduct(productService));
                    break;
                case "5":
                    getListOfProducts(productService);
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
}
