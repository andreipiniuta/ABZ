package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.service.MaterialService;
import sample.service.ProductService;
import sample.service.ServiceFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class showProductController implements Initializable {

    @FXML
    private TextField winID;
    @FXML
    private TableView <Product> TViewProduct;



    public void showAllProducts () throws IOException {

        ObservableList<Product> items = TViewProduct.getItems();
        items.clear();
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        ProductService ps = sf.getProductService();
        items.add((Product) ps.getAllProduct());
    }

    public void showProductByID () throws IOException {

        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);
        System.out.println(ID);

        ObservableList<Product> items = TViewProduct.getItems();
        items.clear();
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        ProductService ps = sf.getProductService();
        items.add (ps.getProductByID(ID));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList <Product> items = this.TViewProduct.getItems();
    }


    public void close() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.showProductWindow);
    }
}
