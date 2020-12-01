package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE users " +
                "(id INT NOT NULL AUTO_INCREMENT," +
                " name VARCHAR(40) NOT NULL," +
                " lastname VARCHAR(40) NOT NULL," +
                " age TINYINT NOT NULL," +
                " PRIMARY KEY (id))";
        toExecuteQuery(sql);
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users";
        toExecuteQuery(sql);
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(name, lastname, age) VALUES(?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException t) {
                System.out.println(t.getMessage());
            }
        }

        System.out.println("User с именем " + name + " добавлен в базу данных.");
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException t) {
                System.out.println(t.getMessage());
            }
        }
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        toExecuteQuery(sql);
    }

    public static void toExecuteQuery(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException t) {
                System.out.println(t.getMessage());
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                resultList.add(new User(name, lastname, age));
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException t) {
                System.out.println(t.getMessage());
            }
        }
        return resultList;
    }
}
