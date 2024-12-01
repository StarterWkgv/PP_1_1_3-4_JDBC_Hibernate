package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    {
        try {
            connection = Util.getConnectionJDBC();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            PreparedStatement pr = connection.prepareStatement("""
                    create table users (
                        id bigint not null auto_increment primary key ,
                        name varchar(30) not null,
                        lastname varchar(30) not null,
                        age tinyint not null
                    )""");
            pr.executeUpdate();
        } catch (SQLException ignored) {

        }

    }

    public void dropUsersTable() {
        try {
            PreparedStatement pr = connection.prepareStatement("drop table users");
            pr.executeUpdate();
        } catch (SQLException ignored) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into users (name, lastname, age) values (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("delete from users where id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from users";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("lastname"), rs.getByte("age"));
                user.setId(rs.getLong("id"));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void cleanUsersTable() {
        try {
            PreparedStatement ps = connection.prepareStatement("truncate table users");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
