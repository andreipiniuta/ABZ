package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.service.MaterialService;
import sample.service.ServiceFactory;

import java.io.IOException;
import java.util.function.Consumer;

public class DeleteMaterialController {

    @FXML
    private TextField winID;

    public void deleteMaterial() throws IOException {
        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Material info...");
        alert.setContentText(ID + "\nis send to Server");
        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType buttonType) {
                if (buttonType == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            }
        });

        ServiceFactory sf = ServiceFactory.getServiceFactory();//у абстрасной Service фабрики  вызвали  единственную Service фабрику Impl
        MaterialService ms = sf.getMaterialService();// с пом фабрику Impl  возвратили объект MaterialService ms
        ms.deleteMaterial(ID);

    }

    public void closeButton(){
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.deleteMaterialWindow);
    }
}
