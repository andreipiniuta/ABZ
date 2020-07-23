package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.service.ServiceFactory;
import sample.service.StaffService;
import sample.service.TruckService;
import sample.service.impl.TruckServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class showTruckController implements Initializable {
    @FXML
    private TextField winID;

    @FXML
    private TableView <Truck> TViewTruck;

    public void showAllTruck () throws IOException {

        ObservableList<Truck> items = TViewTruck.getItems();
        items.clear();
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        TruckService ts = sf.getTruckService();
        items.add((Truck) ts.getAllTruck());
    }

    public void showTruckByID () throws IOException {

        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);
        System.out.println(ID);

        ObservableList<Truck> items = TViewTruck.getItems();
        items.clear();
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        TruckService ts = sf.getTruckService();
        items.add (ts.getTruckByID(ID));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList <Truck> items = this.TViewTruck.getItems();
    }


    public void close() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.showTruckWindow);
    }
}
