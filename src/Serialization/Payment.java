package Serialization;

import java.io.Serializable;

/**
 * Created by Денис on 10/15/16.
 */
public class Payment implements Serializable {
    private static final long serialVersionUID = 1;
    private String customerNumber;
    private String paymentDate;
    private String amount;
    private String checkNumber;

    public Payment(){}

    public Payment(String customerNumber, String paymentDate, String amount) {
        this.customerNumber = customerNumber;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" + customerNumber + "; "
                + paymentDate + "; "
                + amount + '}';
    }
}
