package JDBC_test;

import java.sql.*;

/**
 * Created by Денис on 11/5/16.
 */
public class PrintResult {

    public void printResultSet(ResultSet rs, int ... columns) {
        StringBuilder builder = new StringBuilder();
        try {
            while(rs.next()){
                for(int c : columns){
                    builder.append(rs.getString(c) + "|  ");
                }
                builder.append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(builder.toString());
    }

    public ResultSet getResultSet(String query) {
        Statement statement;
        DbConnector connector = new DbConnector();
        Connection connection = connector.getConnection();
        try {
            statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
