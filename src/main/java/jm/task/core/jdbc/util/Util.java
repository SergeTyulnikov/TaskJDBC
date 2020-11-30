package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=FALSE&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER_NAME = "Serge";
    private static final String PASSWORD = "Mercedes69@";
    private static final String FILENAME = "C:/Users/Bobby/IdeaProjects/TaskJDBC/src/main/java/resources/hibernate.cfg.xml";

    private Connection connection;

    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Fuck the connection!");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure(new File(FILENAME));
        configuration.addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }



}
