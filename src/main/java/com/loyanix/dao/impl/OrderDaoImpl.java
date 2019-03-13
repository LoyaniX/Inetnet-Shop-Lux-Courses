package com.loyanix.dao.impl;

import com.loyanix.dao.ClientDao;
import com.loyanix.dao.DataUitl;
import com.loyanix.dao.OrderDao;
import com.loyanix.dao.ProductDao;
import com.loyanix.domain.Client;
import com.loyanix.domain.Order;
import com.loyanix.domain.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void create(Order order) {
        try {
            Connection connection = DataUitl.getConnection();
            String sqlCreateOrder = "INSERT INTO ORDERS(USER_ID," +
                    "PRICE," +
                    "DATE_OF_CREATE)" +
                    "VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCreateOrder);
            preparedStatement.setLong(1, order.getClient().getId());
            preparedStatement.setBigDecimal(2, order.getOrderPrice());
            preparedStatement.setString(3, order.getDateOfCreate().toString());
            preparedStatement.executeUpdate();
            long insertId = 0L;
            PreparedStatement getLastInsertId = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM ORDERS");
            ResultSet rs = getLastInsertId.executeQuery();
            if (rs.next())
            {
                insertId = rs.getLong("last_insert_id()");
            }
            for (Product product  : order.getProducts()) {
                String sqlAddOrders_Products = "INSERT INTO ORDERS_PRODUCTS(ORDER_ID," +
                        "PRODUCT_ID)" +
                        "VALUES (?,?)";
                preparedStatement = connection.prepareStatement(sqlAddOrders_Products);
                preparedStatement.setLong(1, insertId);
                preparedStatement.setLong(2, product.getId());
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Order getById(Long id) {
        Order order  = null;
        Set<Client> clients = new HashSet<>();
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = DataUitl.getConnection();
            String sqlSelectOrder = "SELECT *, " +
                    "USERS.NAME as USERNAME, " +
                    "PRODUCTS.NAME as PRODUCTNAME, " +
                    "PRODUCTS.PRICE as PRODUCTPRICE " +
                    "FROM ORDERS " +
                    "INNER JOIN USERS ON USERS.ID = ORDERS.USER_ID " +
                    "INNER JOIN ORDERS_PRODUCTS ON ORDERS.ID = ORDERS_PRODUCTS.ORDER_ID " +
                    "INNER JOIN PRODUCTS ON PRODUCTS.ID = ORDERS_PRODUCTS.PRODUCT_ID " +
                    "WHERE ORDERS.ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectOrder);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            long order_id = 0L;
            BigDecimal price = BigDecimal.ZERO;
            LocalDateTime dateTime = null;
            while (resultSet.next()) {
                clients.add(new Client(resultSet.getLong("USER_ID"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getInt("AGE"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PHONE")));
                products.add(new Product(resultSet.getLong("PRODUCT_ID"),
                        resultSet.getString("PRODUCTNAME"),
                        resultSet.getBigDecimal("PRODUCTPRICE"),
                        resultSet.getString("GENDER"),
                        resultSet.getString("SIZE"),
                        resultSet.getInt("QUANTITY")));
                order_id = resultSet.getLong("ID");
                price = resultSet.getBigDecimal("PRICE");
                dateTime = LocalDateTime.parse(resultSet.getString("DATE_OF_CREATE"));
            }
            order = new Order(order_id, clients.iterator().next(), products, price, dateTime);
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void update(Order order) {
        delete(order.getId());
        create(order);
    }

    @Override
    public void delete(Long id) {
        Connection connection = null;
        try {
            connection = DataUitl.getConnection();
            String sqlDeleteUser = "DELETE FROM ORDERS WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteUser);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        Set<Client> clients = new HashSet<>();
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = DataUitl.getConnection();
            String sqlSelectOrder = "SELECT *, " +
                    "USERS.NAME as USERNAME, " +
                    "PRODUCTS.NAME as PRODUCTNAME, " +
                    "PRODUCTS.PRICE as PRODUCTPRICE " +
                    "FROM ORDERS " +
                    "INNER JOIN USERS ON USERS.ID = ORDERS.USER_ID " +
                    "INNER JOIN ORDERS_PRODUCTS ON ORDERS.ID = ORDERS_PRODUCTS.ORDER_ID " +
                    "INNER JOIN PRODUCTS ON PRODUCTS.ID = ORDERS_PRODUCTS.PRODUCT_ID";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectOrder);
            ResultSet resultSet = preparedStatement.executeQuery();
            long order_id = 0L;
            long prevOrder_id = 0L;
            BigDecimal price = BigDecimal.ZERO;
            LocalDateTime dateTime = null;
            while (resultSet.next()) {
                order_id = resultSet.getLong("ID");
                if (prevOrder_id != 0 && prevOrder_id != order_id) {
                    orders.add(new Order(prevOrder_id, clients.iterator().next(), (List<Product>) ((ArrayList<Product>) products).clone(), price, dateTime));
                    products.clear();
                    clients.clear();
                }
                clients.add(new Client(resultSet.getLong("USER_ID"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getInt("AGE"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PHONE")));
                products.add(new Product(resultSet.getLong("PRODUCT_ID"),
                        resultSet.getString("PRODUCTNAME"),
                        resultSet.getBigDecimal("PRODUCTPRICE"),
                        resultSet.getString("GENDER"),
                        resultSet.getString("SIZE"),
                        resultSet.getInt("QUANTITY")));
                prevOrder_id = order_id;
                price = resultSet.getBigDecimal("PRICE");
                dateTime = LocalDateTime.parse(resultSet.getString("DATE_OF_CREATE"));
            }
            orders.add(new Order(order_id, clients.iterator().next(), products, price, dateTime));
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findAllByClient(Long userId) {
        List<Order> orders = new ArrayList<>();
        Set<Client> clients = new HashSet<>();
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = DataUitl.getConnection();
            String sqlSelectOrder = "SELECT *, " +
                    "USERS.NAME as USERNAME, " +
                    "PRODUCTS.NAME as PRODUCTNAME, " +
                    "PRODUCTS.PRICE as PRODUCTPRICE " +
                    "FROM ORDERS " +
                    "INNER JOIN USERS ON USERS.ID = ORDERS.USER_ID " +
                    "INNER JOIN ORDERS_PRODUCTS ON ORDERS.ID = ORDERS_PRODUCTS.ORDER_ID " +
                    "INNER JOIN PRODUCTS ON PRODUCTS.ID = ORDERS_PRODUCTS.PRODUCT_ID " +
                    "WHERE ORDERS.USER_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectOrder);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            long order_id = 0L;
            long prevOrder_id = 0L;
            BigDecimal price = BigDecimal.ZERO;
            LocalDateTime dateTime = null;
            while (resultSet.next()) {
                order_id = resultSet.getLong("ID");
                if (prevOrder_id != 0 && prevOrder_id != order_id) {
                    orders.add(new Order(prevOrder_id, clients.iterator().next(), (List<Product>) ((ArrayList<Product>) products).clone(), price, dateTime));
                    products.clear();
                    clients.clear();
                }
                clients.add(new Client(resultSet.getLong("USER_ID"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getInt("AGE"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PHONE")));
                products.add(new Product(resultSet.getLong("PRODUCT_ID"),
                        resultSet.getString("PRODUCTNAME"),
                        resultSet.getBigDecimal("PRODUCTPRICE"),
                        resultSet.getString("GENDER"),
                        resultSet.getString("SIZE"),
                        resultSet.getInt("QUANTITY")));
                prevOrder_id = order_id;
                price = resultSet.getBigDecimal("PRICE");
                dateTime = LocalDateTime.parse(resultSet.getString("DATE_OF_CREATE"));
            }
            orders.add(new Order(order_id, clients.iterator().next(), products, price, dateTime));
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
}
