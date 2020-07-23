package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import sample.service.ServiceFactory;
import sample.service.TruckService;

import java.io.IOException;
import java.util.function.Consumer;

public class updateTruckController {
    @FXML
    private TextField winID;
    @FXML
    private TextField winType;
    @FXML
    private TextField winNumber;
    @FXML
    private TextField winPayload;


    public void updateTruck() throws IOException {
        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);
        String truckType = winType.getText();
        String truckNumber = winNumber.getText();
        double payload = WindowUtil.readDouble(winPayload);
        Truck truck = new Truck();
        truck.setID(ID);
        truck.setTruckType(truckType);
        truck.setTruckNumber(truckNumber);
        truck.setPayload(payload);
        System.out.println(truck);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Truck info...");
        alert.setContentText(truck.toString() + "\nis send to Server");
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
        TruckService ts = sf.getTruckService();
        ts.saveTruck(truck);
    }
    public void close() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.updateTruckWindow);
    }

}
