package com.loyanix.dao.impl;

import com.loyanix.dao.ClientDao;
import com.loyanix.domain.Client;
import javafx.beans.binding.When;
import jdk.nashorn.internal.ir.WhileNode;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {

    private static ClientDaoImpl instance = null;

    private ClientDaoImpl() {
    }

    public static synchronized ClientDaoImpl getInstance() {
        if (instance == null)
            instance = new ClientDaoImpl();
        return instance;
    }

    @Override
    public void create(Client client) {

        Connection connection = null;
        try {
            Class.forName("org.h2.Driver").newInstance();
            String url = "jdbc:h2:~/InternetShopLux";
            String user = "sa";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error to connect to DB");
            e.printStackTrace();
        }
        String sqlInsertDataOfUser = "INSERT INTO USERS(NAME," +
                "SURNAME," +
                "AGE, " +
                "EMAIL," +
                "PHONE)" +
                "VALUES (?,?,?,?,?)";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertDataOfUser);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setInt(3, client.getAge());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getPhone());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client getById(Long id) {
        Client client = null;
        try {
            Class.forName("org.h2.Driver").newInstance();
            String url = "jdbc:h2:~/InternetShopLux";
            String user = "sa";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);
            assert connection != null;
            String sqlSelectUser = "SELECT * FROM USERS WHERE ID_USER = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectUser);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                client = new Client(resultSet.getLong("ID_USER"),
                        resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getInt("AGE"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PHONE"));
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void update(Client client) {
        try {
            Class.forName("org.h2.Driver").newInstance();
            String url = "jdbc:h2:~/InternetShopLux";
            String user = "sa";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);
            assert connection != null;
            String sqlSelectUser = "UPDATE USERS SET " +
                    "NAME = ?," +
                    "SURNAME = ?," +
                    "AGE = ?," +
                    "EMAIL = ?," +
                    "PHONE = ? " +
                    "WHERE ID_USER = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectUser);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setInt(3, client.getAge());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getPhone());
            preparedStatement.setLong(6, client.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Class.forName("org.h2.Driver").newInstance();
            String url = "jdbc:h2:~/InternetShopLux";
            String user = "sa";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);
            assert connection != null;
            String sqlSelectUser = "DELETE FROM USERS WHERE ID_USER = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectUser);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> clientList = new ArrayList<>();
        try {
            Class.forName("org.h2.Driver").newInstance();
            String url = "jdbc:h2:~/InternetShopLux";
            String user = "sa";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);
            assert connection != null;
            Statement statement = connection.createStatement();
            String sqlSelectUsers = "SELECT * FROM USERS";
            ResultSet resultSet = statement.executeQuery(sqlSelectUsers);
            while (resultSet.next()) {
                clientList.add(new Client(resultSet.getLong("ID_USER"),
                        resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getInt("AGE"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PHONE")));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientList;
    }
}
