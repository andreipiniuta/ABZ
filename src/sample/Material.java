package sample;

public class Material {
    private Integer ID;
    private MaterialName materialName;
    private ProviderName providerName;
    private double amount;
    private double costPerOne;

    public Material() {
    }

    public Material(int ID, MaterialName materialName, ProviderName providerName, double amount, double costPerOne) {
        this.ID = ID;
        this.materialName = materialName;
        this.providerName = providerName;
        this.amount = amount;
        this.costPerOne = costPerOne;
    }

    public MaterialName getMaterialName() {
        return materialName;
    }
    public void setMaterialName(MaterialName materialName) {
        this.materialName = materialName;
    }

    public ProviderName getProviderName() {
        return providerName;
    }

    public void setProviderName(ProviderName providerName) {
        this.providerName = providerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCostPerOne() {
        return costPerOne;
    }

    public void setCostPerOne(double costPerOne) {
        this.costPerOne = costPerOne;
    }

    public double getTotalCost() {
        return amount*costPerOne;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return amount + " of "+ materialName +
                " provided by " + providerName +
                " priced at " + costPerOne +
                ". Total cost is " + getTotalCost();
    }
}
