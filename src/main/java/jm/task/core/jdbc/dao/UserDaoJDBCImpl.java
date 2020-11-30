package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

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
        String sql = "INSERT INTO users(name, lastname, age) VALUES('" + name + "', '" + lastName + "', '" + age + "')";
        toExecuteQuery(sql);
        System.out.println("User с именем " + name + " добавлен в базу данных.");
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = " + (int)id;
        toExecuteQuery(sql);
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        toExecuteQuery(sql);
    }

    public static void toExecuteQuery(String sql) {
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                util.getConnection().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        Util util = new Util();
        String sql = "SELECT * FROM users";
        try(Statement statement = util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                resultList.add(new User(name, lastname, age));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                util.getConnection().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return resultList;
    }
}
