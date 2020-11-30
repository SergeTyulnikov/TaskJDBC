package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Sergey", "Tyulnikov", (byte)25);
        userService.saveUser("Viktor", "Andreev", (byte)23);
        userService.saveUser("Alexander", "Tyulnikov", (byte)30);
        userService.saveUser("Robby", "Bobby", (byte)25);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
