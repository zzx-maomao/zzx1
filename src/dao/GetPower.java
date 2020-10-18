package dao;

import tools.Jdbc;
import vo.Download;
import vo.Resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetPower {
    public GetPower() throws Exception {
    }
    public List<Resource> getresource(String username) throws Exception {
        List<Resource> resources=new ArrayList<>();
        Jdbc jdbc = new Jdbc();
        Connection connection = jdbc.getConnection();
        String sql = "select * from t_resource where resourceid in(select resourceid from t_role_resource where roleid in ( select roleid from t_user_role where userName=?))";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Resource resource=null;
            resource=new Resource(resultSet.getInt("resourceid"),resultSet.getString("resourceName"),resultSet.getString("url"));
            resources.add(resource);
        }
        return resources;
    }
}
