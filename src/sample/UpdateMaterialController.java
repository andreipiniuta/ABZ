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

public class UpdateMaterialController {

    @FXML
    private TextField winID;

    @FXML
    private ComboBox<MaterialName> winName;

    @FXML
    private ComboBox<ProviderName> winProvider;

    @FXML
    private TextField winAmount;

    @FXML
    private TextField winCost;


    public void updateMaterial() throws IOException {

        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);
        MaterialName materialName = winName.getValue();
        ProviderName providerName = winProvider.getValue();
        double amount = WindowUtil.readDouble(winAmount);
        double costPerOne = WindowUtil.readDouble(winCost);
        Material material = new Material();
        material.setID(ID);
        material.setMaterialName(materialName);
        material.setProviderName(providerName);
        material.setAmount(amount);
        material.setCostPerOne(costPerOne);
        material.getTotalCost();
        System.out.println(material);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Material info...");
        alert.setContentText(material.toString() + "\nis send to Server");
        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType buttonType) {
                if (buttonType == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            }
        });
        //сохраняем объект на Server
        ServiceFactory sf = ServiceFactory.getServiceFactory();//у абстрасной Service фабрики  вызвали  единственную Service фабрику Impl
        MaterialService ms = sf.getMaterialService();// с пом фабрику Impl  возвратили объект MaterialService ms
        ms.saveMaterial(material);//у ms объекта вызвали сохранить(для update тоже пойдет)

    }

    public void close() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.updateMaterialWindow);

    }

}
