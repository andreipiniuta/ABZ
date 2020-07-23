package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import sample.service.MaterialService;
import sample.service.ServiceFactory;
import sample.service.StaffService;

import java.io.IOException;
import java.util.function.Consumer;

public class DeleteStaffController {
    @FXML
    private TextField winID;

    public void deleteStaff() throws IOException {
        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Member of staff info...");
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
        StaffService ss = sf.getStaffService();
        ss.deleteStaff(ID);
    }

    public void close() {
        WindowBuilder wbb = WindowBuilder.getIntance();
        wbb.closeWindow(WindowStorage.deleteStaffWindow);
    }
}
