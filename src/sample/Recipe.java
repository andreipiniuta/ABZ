package sample;

public class Recipe {
    private  Integer ID;
    private String productName;
    private  double sandPercent;
    private  double gravelPercent;
    private double bitumPercent;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public double getSandPercent() {
        return sandPercent;
    }

    public void setSandPercent(double sandPercent) {
        this.sandPercent = sandPercent;
    }

    public double getGravelPercent() {
        return gravelPercent;
    }

    public void setGravelPercent(double gravelPercent) {
        this.gravelPercent = gravelPercent;
    }

    public double getBitumPercent() {
        return bitumPercent;
    }

    public void setBitumPercent(double bitumPercent) {
        this.bitumPercent = bitumPercent;
    }


    @Override
    public String toString() {
        return  "The recipe of " + productName + '\'' +
                " is sand=" + sandPercent +
                ", gravel=" + gravelPercent +
                ", bitum=" + bitumPercent;
    }
}
