package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.service.MaterialService;
import sample.service.ServiceFactory;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;


public class showMaterialController implements Initializable {

    @FXML
    private TextField winID;

    @FXML
    private TableView <Material> TViewMaterial;

    public void showAll () throws IOException {

        ObservableList<Material> items = TViewMaterial.getItems();
        items.clear();
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        MaterialService ms = sf.getMaterialService();
        items.add((Material)ms.getAllMaterial());
    }

    public void showByID () throws IOException {

        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);
        System.out.println(ID);

        ObservableList<Material> items = TViewMaterial.getItems();
        items.clear();
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        MaterialService ms = sf.getMaterialService();
        items.add (ms.getMaterialByID(ID));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList <Material> items = this.TViewMaterial.getItems();
    }


    public void closeButton() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.showMaterialWindow);
    }

}
