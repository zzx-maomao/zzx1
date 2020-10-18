package tools;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc {
    public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
    public static final String DBURI = "jdbc:mysql://localhost:3306/work1";
    public static final String DBUSE = "root";
    public static final String DBPASS = "root";

    public Jdbc() throws Exception {
    }

    public Connection getConnection() throws Exception {
        Class.forName(DBDRIVER);
        Connection connection = DriverManager.getConnection(DBURI,DBUSE,DBPASS);
        return connection;
    }
}
