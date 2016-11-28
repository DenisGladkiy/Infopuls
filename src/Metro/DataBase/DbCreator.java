package Metro.DataBase;

import com.mysql.jdbc.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;

/**
 * Created by Денис on 11/18/16.
 */
public class DbCreator {
    String query = "CREATE DATABASE ";

    public void createDb(Connection connection, String name){
        try {
            if(!isExists(connection, name)) {
                PreparedStatement statement = connection.prepareStatement(query + name);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean isExists(Connection connection, String name) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getCatalogs();
        while(resultSet.next()){
            if(resultSet.getString("TABLE_CAT").equals(name)){
                return true;
            }
        }
        return false;
    }


}
