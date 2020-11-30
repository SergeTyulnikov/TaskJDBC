package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Sergey", "Tyulnikov", (byte) 25);
        userService.saveUser("Ekaterina", "Buravtseva", (byte) 24);
        userService.saveUser("Vsevolod", "Volgin", (byte) 27);
        userService.saveUser("Alexander", "Tyulnikov", (byte) 20);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
