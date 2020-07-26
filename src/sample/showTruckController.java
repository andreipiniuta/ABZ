package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.service.ServiceFactory;
import sample.service.TruckService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
        List<Truck> allTruck = ts.getAllTruck();
        for (Truck truck:allTruck) {
            items.add(truck);
        }
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
