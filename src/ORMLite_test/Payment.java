package ORMLite_test;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Денис on 11/5/16.
 */
@DatabaseTable(tableName = "payments")
public class Payment {

    @DatabaseField(canBeNull = false,useGetSet = true)
    private int customerNumber;

    @DatabaseField(id = true)
    private String checkNumber;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private Date paymentDate;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private double amount;

    public Payment(){}

    @Override
    public String toString() {
        return "Payment{" +
                "customerNumber=" + customerNumber +
                ", checkNumber='" + checkNumber + '\'' +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                '}';
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
