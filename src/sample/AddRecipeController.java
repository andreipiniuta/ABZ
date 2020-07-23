package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import sample.service.RecipeService;
import sample.service.ServiceFactory;

import java.io.IOException;
import java.util.function.Consumer;

public class AddRecipeController {
    @FXML
    private TextField productName;

    @FXML
    private TextField sandPrct;

    @FXML
    private TextField gravelPrct;

    @FXML
    private TextField bitumPrct;

    public void addRecipe() throws IOException {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.addRecipeWindow);
        String productN = productName.getText();
        Double sand =WindowUtil.readDouble(sandPrct);
        Double gravel =WindowUtil.readDouble(gravelPrct);
        Double bitum =WindowUtil.readDouble(bitumPrct);
        Recipe recipe = new Recipe();
        recipe.setProductName(productN);
        recipe.setSandPercent(sand);
        recipe.setGravelPercent(gravel);
        recipe.setBitumPercent(bitum);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Recipe info...");
        alert.setContentText(recipe.toString() + "\n is send to Server");
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
        RecipeService rs = sf.getRecipeService();
        rs.saveRecipe(recipe);
    }

    public void closeButton() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.closeWindow(WindowStorage.addRecipeWindow);
    }
}
