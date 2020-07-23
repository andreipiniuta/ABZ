package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import sample.service.MaterialService;
import sample.service.ServiceFactory;
import sample.service.TruckService;

import java.io.IOException;
import java.util.function.Consumer;

public class DeleteTruckController {

    @FXML
    private TextField winID;

    public void deleteTruck() throws IOException {
        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Truck info...");
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
        TruckService ts = sf.getTruckService();
        ts.deleteTruck(ID);
    }

    public void close(){
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.deleteTruckWindow);
    }
}
