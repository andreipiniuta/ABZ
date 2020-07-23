package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.service.RecipeService;
import sample.service.ServiceFactory;
import sample.service.StaffService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class showStaffController implements Initializable {

    @FXML
    private TextField winID;

    @FXML
    private TableView <Staff> TViewStaff;


    public void  showAllStaff() throws IOException {

        ObservableList<Staff> items = TViewStaff.getItems();
        items.clear();
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        StaffService ss = sf.getStaffService();
        items.add((Staff) ss.getAllStaff());

    }

    public void showStaffByID() throws IOException {
        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);
        System.out.println(ID);

        ObservableList<Staff> items = TViewStaff.getItems();
        items.clear();
        ServiceFactory sf = ServiceFactory.getServiceFactory();
         StaffService ss = sf.getStaffService();
         items.add(ss.getStaffByID(ID));

    }


    public void close() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.showStaffWindow);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList <Staff> items = this.TViewStaff.getItems();

    }
}
