package dao;

import tools.Jdbc;
import vo.City;
import vo.Province;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetProvinceAndCity {
public List<Province> GetProvince() throws Exception {
    List<Province>provinceslist=new ArrayList<>();
    Jdbc jdbc = new Jdbc();
    Connection connection = jdbc.getConnection();
    String SQL = "SELECT * FROM t_province WHERE id like '%'";
    PreparedStatement preparedStatement = connection.prepareStatement(SQL);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()){
        Province province=new Province(resultSet.getInt("id"),resultSet.getString("name"));
        provinceslist.add(province);
    }
    resultSet.close();
    preparedStatement.close();
    connection.close();
    return provinceslist;
}
    public List<City> GetCity(String provincename) throws Exception {
        List<City> citylist=new ArrayList<>();
        Jdbc jdbc = new Jdbc();
        Connection connection = jdbc.getConnection();
        String SQL = "SELECT * FROM t_city WHERE provinceid in(SELECT id FROM t_province WHERE name =?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1,provincename);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            City city=new City(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("provinceid"));
            citylist.add(city);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return citylist;
    }

}

