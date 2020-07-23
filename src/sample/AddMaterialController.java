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

public class AddMaterialController {
    @FXML
    private ComboBox<MaterialName> nameMaterial;

    @FXML
    private ComboBox<ProviderName> nameProvider;

    @FXML
    private TextField amountMaterial;

    @FXML
    private TextField costMaterial;



    public void addMaterial () throws IOException {

        MaterialName materialName = nameMaterial.getValue();
        ProviderName providerName = nameProvider.getValue();
        double amount = WindowUtil.readDouble(amountMaterial);
        double costPerOne = WindowUtil.readDouble(costMaterial);
        Material material = new Material();
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
        //сохраняем объект на Serer
        ServiceFactory sf = ServiceFactory.getServiceFactory();//у абстрасной Service фабрики  вызвали  единственную Service фабрику Impl
        MaterialService ms = sf.getMaterialService();// с пом фабрику Impl  возвратили объект MaterialService ms
        ms.saveMaterial(material);//у ms объекта вызвали сохранить

    }

    public void closeButton() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.addMaterialWindow);
        }

}
