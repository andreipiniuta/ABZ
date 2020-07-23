package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import sample.service.ProductService;
import sample.service.ServiceFactory;

import java.io.IOException;
import java.util.function.Consumer;

public class AddProductController {
    @FXML
    private TextField prodName;

    @FXML
    private TextField address;

    @FXML
    private TextField amount;

    public void addProduct() throws IOException {
         String nameprd = prodName.getText();
         String adrs = address.getText();
         double amt = WindowUtil.readDouble(amount);
         Product product = new Product();
         product.setProductName(nameprd);
         product.setConstructionAddress(adrs);
         product.setProductAmount(amt);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Product info...");
        alert.setContentText(product.toString() + "\n is send to Server");
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

    public void closeButton() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.addProductWindow);
    }
}
