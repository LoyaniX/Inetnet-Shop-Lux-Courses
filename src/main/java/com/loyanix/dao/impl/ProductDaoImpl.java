package com.loyanix.dao.impl;

import com.loyanix.dao.DataUitl;
import com.loyanix.dao.ProductDao;
import com.loyanix.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void create(Product product) {
        try {
            Connection connection = DataUitl.getConnection();
            String sqlCreateProducts = "INSERT INTO PRODUCTS(NAME," +
                    "PRICE," +
                    "GENDER, " +
                    "SIZE," +
                    "QUANTITY)" +
                    "VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCreateProducts);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setString(3, product.getGender());
            preparedStatement.setString(4, product.getSize());
            preparedStatement.setInt(5, product.getQuantity());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getById(Long id) {
        Product product = null;
        try {
            Connection connection = DataUitl.getConnection();
            String sqlSelectProduct = "SELECT * FROM PRODUCTS WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectProduct);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product = new Product(resultSet.getLong("ID"),
                        resultSet.getString("NAME"),
                        resultSet.getBigDecimal("PRICE"),
                        resultSet.getString("GENDER"),
                        resultSet.getString("SIZE"),
                        resultSet.getInt("QUANTITY"));
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void update(Product product) {
        try {
            Connection connection = DataUitl.getConnection();
            String sqlCreateProducts = "UPDATE PRODUCTS SET" +
                    "NAME = ?," +
                    "PRICE = ?," +
                    "GENDER = ?, " +
                    "SIZE = ?," +
                    "QUANTITY = ?" +
                    "WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCreateProducts);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setString(3, product.getGender());
            preparedStatement.setString(4, product.getSize());
            preparedStatement.setInt(5, product.getQuantity());
            preparedStatement.setLong(6, product.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = DataUitl.getConnection();
            String sqlDeleteProducts = "DELETE FROM PRODUCTS WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteProducts);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = DataUitl.getConnection();
            String sqlSelectProducts = "SELECT * FROM PRODUCTS";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectProducts);
            while (resultSet.next()) {
                productList.add(new Product(resultSet.getLong("ID"),
                        resultSet.getString("NAME"),
                        resultSet.getBigDecimal("PRICE"),
                        resultSet.getString("GENDER"),
                        resultSet.getString("SIZE"),
                        resultSet.getInt("QUANTITY")));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
