package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.service.MaterialService;
import sample.service.RecipeService;
import sample.service.ServiceFactory;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class showRecipeController implements Initializable {
    @FXML
    private TextField winID;

    @FXML
    private TableView <Recipe> TViewRecipe;

    public void showAllRecipe() throws IOException {

        ObservableList<Recipe> items = TViewRecipe.getItems();
        items.clear();
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        RecipeService rs = sf.getRecipeService();
        items.add((Recipe)rs.getAllRecipe());
    }

    public void showRecipeByID() throws IOException {
        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);
        System.out.println(ID);

        ObservableList<Recipe> items = TViewRecipe.getItems();
        items.clear();
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        RecipeService rs = sf.getRecipeService();
        items.add (rs.getRecipeByID(ID));
    }

    public void closeRecipe(){
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.showRecipeWindow);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList <Recipe> items = this.TViewRecipe.getItems();
    }
}
