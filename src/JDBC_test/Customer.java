package JDBC_test;

import ORMLite_test.Payment;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Денис on 11/8/16.
 */
@DatabaseTable(tableName = "customers")
public class Customer {

//    @DatabaseField(canBeNull = false, foreign = true, foreignColumnName = "customerNumber")
//    private Payment payment;

    @DatabaseField(id = true)
    private int customerNumber;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String customerName;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String contactLastName;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String contactFirstName;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String phone;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String addressLine1;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String addressLine2;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String city;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String state;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String postalCode;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String country;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private int salesRepEmployeeNumber;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private double creditLimit;

    public Customer(){}

    @Override
    public String toString() {
        return "Customer{" +
                "customerNumber=" + customerNumber +
                ", customerName='" + customerName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", creditLimit=" + creditLimit +
                '}';
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSalesRepEmployeeNumber() {
        return salesRepEmployeeNumber;
    }

    public void setSalesRepEmployeeNumber(int salesRepEmployeeNumber) {
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }
}
