package dao;

import tools.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class NewUser {
    public void InsertNerUser(String usename,String name,String password,String email,String province,String city )throws  Exception{
        Jdbc jdbc = new Jdbc();
        Connection connection = jdbc.getConnection();
        String SQL = "insert into t_user values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, usename);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, name);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, province);
        preparedStatement.setString(6, city);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
