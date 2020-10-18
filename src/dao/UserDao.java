package dao;

import tools.Jdbc;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public UserDao() {
    }

    public User getUser(String username) throws Exception {
        Jdbc jdbc = new Jdbc();
        Connection connection = jdbc.getConnection();
        String SQL = "SELECT password,charName FROM t_user WHERE userName =?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user=null;
        while (resultSet.next()) {
            user = new User(username, resultSet.getString("password"), resultSet.getString("charName"));

        }
        return user;
    }
}

