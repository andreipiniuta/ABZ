package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import sample.service.MaterialService;
import sample.service.ProductService;
import sample.service.ServiceFactory;

import java.io.IOException;
import java.util.function.Consumer;

public class DeleteProductController {


    @FXML
    private TextField winID;

    public void deleteProduct() throws IOException {
        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Product info...");
        alert.setContentText(ID + "\nis send to Server");
        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType buttonType) {
                if (buttonType == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            }
        });

        ServiceFactory sf = ServiceFactory.getServiceFactory();
        ProductService ps = sf.getProductService();
        ps.deleteProduct(ID);

    }

    public void close(){
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.deleteProductWindow);
    }
}
