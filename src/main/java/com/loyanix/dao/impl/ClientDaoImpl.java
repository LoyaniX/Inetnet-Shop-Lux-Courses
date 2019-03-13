package com.loyanix.dao.impl;

import com.loyanix.dao.ClientDao;
import com.loyanix.dao.DataUitl;
import com.loyanix.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {

    private static ClientDaoImpl instance = null;
    private String sqlCreateUser = "INSERT INTO USERS(NAME, SURNAME, AGE, EMAIL, PHONE) VALUES (?,?,?,?,?)";
    private String sqlSelectUser = "SELECT * FROM USERS WHERE ID = ?";
    private String sqlUpdateUser = "UPDATE USERS SET NAME = ?," +
                                                    "SURNAME = ?," +
                                                    "AGE = ?," +
                                                    "EMAIL = ?," +
                                                    "PHONE = ? " +
                                                    "WHERE ID = ?";
    private String sqlDeleteUser = "DELETE FROM USERS WHERE ID = ?";
    private String sqlSelectUsers = "SELECT * FROM USERS";



    private ClientDaoImpl() {
    }

    public static synchronized ClientDaoImpl getInstance() {
        if (instance == null)
            instance = new ClientDaoImpl();
        return instance;
    }

    @Override
    public void create(Client client) {

        try {
            Connection connection = DataUitl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCreateUser);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setInt(3, client.getAge());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getPhone());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client getById(Long id) {
        Client client = null;
        try {
            Connection connection = DataUitl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectUser);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                client = new Client(resultSet.getLong("ID"),
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
            Connection connection = DataUitl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateUser);
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
            Connection connection = DataUitl.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteUser);
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
            Connection connection = DataUitl.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectUsers);
            while (resultSet.next()) {
                clientList.add(new Client(resultSet.getLong("ID"),
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
