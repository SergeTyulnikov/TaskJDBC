package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.exception.SQLGrammarException;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery("CREATE TABLE users " +
                "(id INT NOT NULL AUTO_INCREMENT," +
                " name VARCHAR(40) NOT NULL," +
                " lastname VARCHAR(40) NOT NULL," +
                " age TINYINT NOT NULL," +
                " PRIMARY KEY (id))");
        try {
            sqlQuery.executeUpdate();
        } catch (SQLGrammarException e) {
            System.out.println(e.getCause().getMessage());
        }
        session.getTransaction().commit();
        session.close();
        factory.close();

    }

    @Override
    public void dropUsersTable() {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery("DROP TABLE users");
        try {
            sqlQuery.executeUpdate();
        } catch (SQLGrammarException e) {
            System.out.println(e.getCause().getMessage());
        }
        session.getTransaction().commit();
        session.close();
        factory.close();


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        try {
            session.save(new User(name, lastName, age));
        } catch (NullPointerException e) {
            System.out.println("You are trying to save invalid User!");
        }
        session.getTransaction().commit();
        session.close();
        factory.close();
        System.out.println("User с именем " + name + " добавлен в базу данных.");
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User WHERE id = " + id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        Query query = session.createQuery("FROM User");
        List<User> users = query.list();
        session.close();
        factory.close();
        return users;

    }

    @Override
    public void cleanUsersTable() {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery("TRUNCATE TABLE users");
        sqlQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
