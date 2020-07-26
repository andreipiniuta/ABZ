package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.service.ProductService;
import sample.service.ServiceFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
        List<Product> allProduct = ps.getAllProduct();
        for (Product product:allProduct) {
            items.add(product);
        }
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
