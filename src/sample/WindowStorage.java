package sample;

public enum WindowStorage {
    mainWindow ("main.fxml", "General panel", false, 700, 250),

    addMaterialWindow ("addMaterial.fxml", "Materials acception", true, 300, 250),

    addRecipeWindow ("addRecipe.fxml", "Recipe acception", true, 300, 250),

    addProductWindow ("addProduct.fxml", "Product acception", true, 300, 250),

    addTruckWindow ("addTruck.fxml", "Truck acception", true, 290, 240),

    addStaffWindow ("addStaff.fxml", "Staff acception", true, 300, 240),

    showMaterialWindow("showMaterial.fxml","Available material", true,300,280),

    showRecipeWindow("showRecipe.fxml","Available recipe", true,300,280),

    showProductWindow("showProduct.fxml","Made products", true,300,280),

    showTruckWindow("showTruck.fxml","Available trucks", true,300,280),

    showStaffWindow("showStaff.fxml","Staff", true,300,280),

    updateMaterialWindow("updateMaterial.fxml","update material", true,300,280),

    updateRecipeWindow("updateRecipe.fxml","update recipe", true,300,280),

    updateProductWindow("updateProduct.fxml","update product", true,300,280),

    updateStaffWindow("updateStaff.fxml","update staff", true,300,280),

    updateTruckWindow("updateTruck.fxml","update truck", true,300,280),

    deleteMaterialWindow("deleteMaterial.fxml","delete material", true,300,280),

    deleteRecipeWindow("deleteRecipe.fxml","delete recipe", true,300,280),

    deleteProductWindow("deleteProduct.fxml","delete product", true,300,280),

    deleteStaffWindow("deleteStaff.fxml","delete member of staff", true,300,280),

    deleteTruckWindow("deleteTruck.fxml","delete truck", true,300,280);

    private String xmlUi;//адрес FXML

    private String title;// назв заголовка

    private boolean modalWindow;// модальное или не модальное

    private int width;// размер

    private int height;// размер

    WindowStorage(String xmlUi, String title, boolean modalWindow, int width, int height) {
        this.xmlUi = xmlUi;
        this.title = title;
        this.modalWindow = modalWindow;
        this.width = width;
        this.height = height;
    }

    public String getXmlUi() {
        return xmlUi;
    }

    public void setXmlUi(String xmlUi) {
        this.xmlUi = xmlUi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isModalWindow() {
        return modalWindow;
    }

    public void setModalWindow(boolean modalWindow) {
        this.modalWindow = modalWindow;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
