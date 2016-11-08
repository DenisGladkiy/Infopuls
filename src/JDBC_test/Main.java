package JDBC_test;

import java.sql.*;

/**
 * Created by Денис on 11/5/16.
 */
public class Main {

    private static String query1 = "select * from products limit 5";
    private static String query2 = "SELECT customers.customerName, customers.country FROM customers " +
                                    "WHERE customers.country='France' OR customers.country='Spain'";
    private static String query3 = "SELECT customers.customerName, orders.orderNumber, orders.orderDate, orders.status " +
                                    "FROM customers, orders \n" +
                                    "WHERE customers.customerNumber = orders.customerNumber " +
                                    "AND orders.status NOT LIKE 'Shipped';";
    private static String query4 = "SELECT payments.paymentDate, date_format(payments.paymentDate,'%Y-%m'), " +
                                    "SUM(payments.amount), COUNT(*) FROM payments \n" +
                                    "WHERE payments.paymentDate BETWEEN STR_TO_DATE('20040501', '%Y%m%d') " +
                                    "AND STR_TO_DATE('20040731', '%Y%m%d') " +
                                    "GROUP BY date_format(payments.paymentDate,'%Y-%m');\n";


    public static void main(String[] args) {
        PrintResult printer = new PrintResult();
        ResultSet res = printer.getResultSet(query1);
        printer.printResultSet(res, 1, 2, 3, 4, 5, 6, 7, 8, 9);
//        res = printer.getResultSet(query2);
//        printer.printResultSet(res, 1, 2);
//        res = printer.getResultSet(query3);
//        printer.printResultSet(res, 1, 2, 3, 4);
//        res = printer.getResultSet(query4);
//        printer.printResultSet(res, 2, 3, 4);
    }

}
