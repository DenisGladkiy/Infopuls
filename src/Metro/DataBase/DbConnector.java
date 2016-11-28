package Metro.DataBase;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Денис on 11/18/16.
 */
public class DbConnector {

    String url = "jdbc:mysql://localhost/";
    String driver = "com.mysql.jdbc.Driver";
    String user = "root";
    String password = "root";

    public Connection connectDb(String name){
        Connection connection = null;
        try {
            Class.forName(driver);
           // connection = DriverManager.getConnection("jdbc:mysql://localhost/classicmodels?"
           //         + "user=user1&password=pass1");
            connection = DriverManager.getConnection(url + name + "?" + "user=" + user + "&password=" + password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public ConnectionSource getConnectionSource(String url){
        try {
            return new JdbcConnectionSource(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
