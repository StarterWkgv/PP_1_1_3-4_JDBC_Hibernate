package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
//        userService.dropUsersTable();
        userService.createUsersTable();
//        IntStream.rangeClosed(1,4).forEach(e -> userService.saveUser("User" + e, "Userson", (byte) (20 + e)));
//        userService.getAllUsers().forEach(System.out::println);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
