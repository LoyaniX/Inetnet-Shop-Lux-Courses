package com.loyanix.dao;

import com.loyanix.exeptions.BusinessException;
import com.loyanix.services.ClientService;
import com.loyanix.services.OrderService;
import com.loyanix.services.ProductService;
import com.loyanix.services.dto.ClientDto;
import com.loyanix.services.dto.OrderDto;
import com.loyanix.services.dto.ProductDto;
import org.apache.commons.dbcp.BasicDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataUitl {

    private static Connection connection;

    public static void createTebles(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver").newInstance();
            String url = "jdbc:h2:~/InternetShopLux";
            String user = "sa";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e){
            System.out.println("Error to connect to DB");
            e.printStackTrace();
        }
        String sqlDropTableUsers = "DROP TABLE IF EXISTS USERS";
        String sqlDropTableProducts = "DROP TABLE IF EXISTS PRODUCTS";
        String sqlDropTableOrders = "DROP TABLE IF EXISTS ORDERS";
        String sqlDropTableOrders_Products = "DROP TABLE IF EXISTS ORDERS_PRODUCTS";

        String sqlCreateTableUsers = "CREATE TABLE USERS(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "NAME VARCHAR(32) NOT NULL," +
                "SURNAME VARCHAR(32) NOT NULL," +
                "AGE INT NOT NULL," +
                "EMAIL VARCHAR(32) NOT NULL," +
                "PHONE VARCHAR(32) NOT NULL)";

        String sqlCreateTableProducs = "CREATE TABLE PRODUCTS(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "NAME VARCHAR(32) NOT NULL," +
                "PRICE NUMBER NOT NULL," +
                "GENDER VARCHAR(32)," +
                "SIZE VARCHAR(32)," +
                "QUANTITY INT NOT NULL)";

        String sqlCreateTableOrders = "CREATE TABLE ORDERS(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "USER_ID INT NOT NULL REFERENCES USERS (ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "PRICE NUMBER NOT NULL," +
                "DATE_OF_CREATE VARCHAR(32) NOT NULL)";

        String sqlCreateTableOrders_Products = "CREATE TABLE ORDERS_PRODUCTS(" +
                "ORDER_ID INT NOT NULL," +
                "PRODUCT_ID INT NOT NULL," +
                "CONSTRAINT ORDER_FK FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "CONSTRAINT PRODUCT_FK FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS (ID) ON DELETE NO ACTION ON UPDATE NO ACTION)";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlDropTableUsers);
            statement.executeUpdate(sqlCreateTableUsers);
            statement.executeUpdate(sqlDropTableProducts);
            statement.executeUpdate(sqlCreateTableProducs);
            statement.executeUpdate(sqlDropTableOrders);
            statement.executeUpdate(sqlCreateTableOrders);
            statement.executeUpdate(sqlDropTableOrders_Products);
            statement.executeUpdate(sqlCreateTableOrders_Products);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver").newInstance();
        String url = "jdbc:h2:~/InternetShopLux";
        String user = "sa";
        String password = "";
        connection = DriverManager.getConnection(url, user, password);
    }

    public static Connection getConnection() {
        return connection;
    }
    public static void addExampleData(ClientService clientService, ProductService productService, OrderService orderService) {
        productService.create(new ProductDto("Tesla", new BigDecimal(123456789), "unisex", "Model X", 12));
        productService.create(new ProductDto("Tesla", new BigDecimal(2412312), "male", "Model S", 123));
        productService.create(new ProductDto("Iphone", new BigDecimal(1000), "unisex", "Xs max", 56));
        productService.create(new ProductDto("T-short", new BigDecimal(12), "female", "xs", 120));

        clientService.create(new ClientDto("Max", "Loyan", 21, "loyanmaksim@gmail.com", "+380978777774"));
        clientService.create(new ClientDto("Maksim", "Loyan", 25, "loyanmaksim@gmail.com", "+380978777777"));
        clientService.create(new ClientDto("John", "Shelvy", 12, "loyanmaksim@gmail.com", "+380675452254"));
        clientService.create(new ClientDto("Marina", "Kayl", 65, "loyanmaksim@gmail.com", "+380505696886"));

        List<ProductDto> exampleProducts = productService.findAll();
        List<ProductDto> productDto = new ArrayList<>();
        try {
            productDto.add(productService.getById(2L));
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        for (long i = 1; i < 5; i++) {
            ClientDto clientDto = null;
            try {
                clientDto = clientService.getById(i);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            orderService.create(new OrderDto(clientDto, exampleProducts, null, null));
        }
        ClientDto clientDto = null;
        try {
            clientDto = clientService.getById(2L);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        orderService.create(new OrderDto(clientDto, productDto, null, null));
    }
}
