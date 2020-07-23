package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import sample.service.ServiceFactory;
import sample.service.StaffService;

import java.io.IOException;
import java.util.function.Consumer;

public class UpdateStaffController {
    @FXML
    private TextField winID;
    @FXML
    private TextField winName;
    @FXML
    private TextField winSurname;
    @FXML
    private TextField winPosition;
    @FXML
    private TextField winSalary;

    public void updateStaff() throws IOException {
        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);
        String name = winName.getText();
        String surname = winSurname.getText();
        String position = winPosition.getText();
        double salary = WindowUtil.readDouble(winSalary);
        Staff staff  = new Staff();
        staff.setID(ID);
        staff.setName(name);
        staff.setSurname(surname);
        staff.setPosition(position);
        staff.setSalary(salary);
        System.out.println(staff);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Staff info...");
        alert.setContentText(staff.toString() + "\nis send to Server");
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
        StaffService ss = sf.getStaffService();
        ss.saveStaff(staff);
    }

    public void close() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.updateStaffWindow);
    }
}
