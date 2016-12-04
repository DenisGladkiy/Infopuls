package XML;

import java.util.List;

/**
 * Created by Денис on 12/3/16.
 */
public class Tariff {
    String name;
    String operator;
    Double payroll;
    Double sms_price;
    CallPrices callPrices;
    Parameters parameters;

    public Tariff(String name, String operator, Double payroll, Double sms_price, CallPrices callPrices, Parameters parameters) {
        this.name = name;
        this.operator = operator;
        this.payroll = payroll;
        this.sms_price = sms_price;
        this.callPrices = callPrices;
        this.parameters = parameters;
    }

    public Tariff(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Double getPayroll() {
        return payroll;
    }

    public void setPayroll(Double payroll) {
        this.payroll = payroll;
    }

    public Double getSms_price() {
        return sms_price;
    }

    public void setSms_price(Double sms_price) {
        this.sms_price = sms_price;
    }

    public CallPrices getCallPrices() {
        return callPrices;
    }

    public void setCallPrices(CallPrices callPrices) {
        this.callPrices = callPrices;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                ", operator='" + operator + '\'' +
                ", payroll=" + payroll +
                ", sms_price=" + sms_price +
                ", callPrices=" + callPrices +
                ", parameters=" + parameters +
                '}' + "\n";
    }
}
