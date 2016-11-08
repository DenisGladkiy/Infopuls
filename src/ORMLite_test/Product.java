package ORMLite_test;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Денис on 11/5/16.
 */
@DatabaseTable(tableName = "products")
public class Product {
    public static String PRODUCT_LINE = "productLine";

    @DatabaseField(id = true)
    private String productCode;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String productName;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String productLine;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String productScale;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String productVendor;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String productDescription;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private int quantityInStock;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private double buyPrice;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private double MSRP;

    public Product(){}

    @Override
    public String toString() {
        return "Product{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productLine='" + productLine + '\'' +
                '}';
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getProductScale() {
        return productScale;
    }

    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    public String getProductVendor() {
        return productVendor;
    }

    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getMSRP() {
        return MSRP;
    }

    public void setMSRP(double MSRP) {
        this.MSRP = MSRP;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
