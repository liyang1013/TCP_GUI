package Client.Dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;

public class User implements Serializable {
    Statement statement=Jdbc.getStatement();

    public boolean equals(String name,String password) {
        String sql = "SELECT  passwd  FROM login WHERE name=" + "'"+name+"'";
        boolean flag=false;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String passwd = resultSet.getString("passwd");
                flag= password.equals(passwd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return flag;
        }

    }
    public void Register(String name, int passwd){
        String sql= "INSERT INTO login(name,passwd) VALUES('" +name+"',"+passwd+")";
        try {
            statement.execute(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
