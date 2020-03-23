package Client.Dao;

import java.sql.*;

public class Jdbc {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/ly";
    static final String USER = "root";
    static final String PASS = "961013ly";
    private static Connection connection = null;
    private static Statement statement = null;

    static {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    public  static Statement getStatement(){
        return statement;
    }

}
