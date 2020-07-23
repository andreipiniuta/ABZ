package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import sample.service.ServiceFactory;
import sample.service.TruckService;

import java.io.IOException;
import java.util.function.Consumer;

public class AddTruckController {

    @FXML
    private TextField type;

    @FXML
    private TextField number;

    @FXML
    private TextField payload;

    public  void addTruck() throws IOException {

        String typeTruck = type.getText();
        String numberTruck = number.getText();
        double payloadTruck = WindowUtil.readDouble(payload);
        Truck truck = new Truck();
        truck.setTruckType(typeTruck);
        truck.setTruckNumber(numberTruck);
        truck.setPayload(payloadTruck);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Truck info...");
        alert.setContentText(truck.toString() + "\n is send to Server");
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

    public void closeButton() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.addTruckWindow);
    }
}
