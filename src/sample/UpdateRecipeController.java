package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import sample.service.RecipeService;
import sample.service.ServiceFactory;

import java.io.IOException;
import java.util.function.Consumer;

public class UpdateRecipeController {

    @FXML
    private TextField winID;

    @FXML
    private TextField winName;

    @FXML
    private TextField winSand;

    @FXML
    private TextField winGravel;

    @FXML
    private TextField winBitum;

    public void updateRecipe() throws IOException {
        String IDStr = winID.getText();
        Integer ID = Integer.parseInt(IDStr);
        String productName = winName.getText();
        double sandPercent = WindowUtil.readDouble(winSand);
        double gravelPercent = WindowUtil.readDouble(winGravel);
        double bitumPercent = WindowUtil.readDouble(winBitum);
        Recipe recipe = new Recipe();
        recipe.setID(ID);
        recipe.setProductName(productName);
        recipe.setSandPercent(sandPercent);
        recipe.setGravelPercent(gravelPercent);
        recipe.setBitumPercent(bitumPercent);
        System.out.println(recipe);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Recipe info...");
        alert.setContentText(recipe.toString() + "\nis send to Server");
        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType buttonType) {
                if (buttonType == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            }
        });
        //сохраняем объект на Server
        ServiceFactory sf = ServiceFactory.getServiceFactory();//у абстрасной Service фабрики  вызвали  единственную Service фабрику Impl
        RecipeService rs = sf.getRecipeService();// с пом фабрику Impl  возвратили объект RecipeService rs
        rs.saveRecipe(recipe);//у rs объекта вызвали сохранить(для update тоже пойдет)
    }

    public void close() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.updateRecipeWindow);

    }




}
