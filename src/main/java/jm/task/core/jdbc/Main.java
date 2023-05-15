package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("n_1","tLN_1", (byte) 27);
        userService.saveUser("n_2","tLN_2", (byte) 18);
        userService.saveUser("n_3","tLN_3", (byte) 34);
        userService.saveUser("n_4","tLN_4", (byte) 52);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
