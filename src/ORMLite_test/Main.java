package ORMLite_test;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 11/5/16.
 */
public class Main {

    static ConnectionSource connectionSource;
    static String user = "user1";
    static String pass = "pass1";
    static String databaseURL = "jdbc:mysql://localhost/classicmodels?" + "user=" + user + "&password=" + pass;

    public static void main(String[] args) {
        Dao<Product, String> productDao;
        Dao<Payment, String> paymentDao;
        List<Product> products;
        List<Payment> payments;
        try {
            connectionSource = new JdbcConnectionSource(databaseURL);
            productDao = DaoManager.createDao(connectionSource, Product.class);
            paymentDao = DaoManager.createDao(connectionSource, Payment.class);
            QueryBuilder<Product, String> productQb = productDao.queryBuilder();
            QueryBuilder<Payment, String> paymentQb = paymentDao.queryBuilder();
            productQb.where().eq(Product.PRODUCT_LINE, "Ships");
            paymentQb.where().eq("customerNumber", 141);
            products = productQb.query();
            payments = paymentQb.query();
            for(Product p : products){
                System.out.println(p);
            }
            System.out.println(payments.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
