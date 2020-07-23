package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import sample.service.ProductService;
import sample.service.ServiceFactory;

import java.io.IOException;
import java.util.function.Consumer;

public class UpdateProductController {
    @FXML
    private TextField winID;
    @FXML
    private TextField winName;
    @FXML
    private TextField winAmount;
    @FXML
    private TextField winConAddress;

    public void updateProduct() throws IOException {
        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);
        String  productName = winName.getText();
        double productAmount = WindowUtil.readDouble(winAmount);
        String constructionAddress = winConAddress.getText();
        Product product = new Product();
        product.setID(ID);
        product.setProductName(productName);
        product.setProductAmount(productAmount);
        product.setConstructionAddress(constructionAddress);
        System.out.println(product);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Product info...");
        alert.setContentText(product.toString() + "\nis send to Server");
        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType buttonType) {
                if (buttonType == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            }
        });
        //сохраняем объект на Server
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        ProductService ps = sf.getProductService();
        ps.saveProduct(product);
    }

    public void close() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.updateProductWindow);
    }
}
