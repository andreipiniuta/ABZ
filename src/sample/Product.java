package sample;

public class Product {
    private  Integer ID;
    private String productName;
    double productAmount;
    private String constructionAddress;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(double productAmount) {
        this.productAmount = productAmount;
    }

    public String getConstructionAddress() {
        return constructionAddress;
    }

    public void setConstructionAddress(String constructionAddress) {
        this.constructionAddress = constructionAddress;
    }

    @Override
    public String toString() {
        return productAmount + " of " + productName + '\'' +
                " is delivered to " + constructionAddress;
    }
}
