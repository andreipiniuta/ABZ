package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import sample.service.RecipeService;
import sample.service.ServiceFactory;
import sample.service.StaffService;

import java.io.IOException;
import java.util.function.Consumer;

public class AddStaffController {
    @FXML
    private TextField namefx;

    @FXML
    private TextField surnamefx;

    @FXML
    private TextField positionfx;

    @FXML
    private TextField salaryfx;


    public void addStaff() throws IOException {
        String name = namefx.getText();
        String surname = surnamefx.getText();
        String  position = positionfx.getText();
        double salary = WindowUtil.readDouble(salaryfx);

        Staff staff = new Staff();
        staff.setName(name);
        staff.setSurname(surname);
        staff.setPosition(position);
        staff.setSalary(salary);

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
        //сохраняем объект на Serer
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        StaffService ss = sf.getStaffService();
        ss.saveStaff(staff);
    }

    public void closeButton() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.addStaffWindow);
    }
}
