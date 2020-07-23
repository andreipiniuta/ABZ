package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WindowBuilder {

    Map <WindowStorage, Stage> allWindowsList = new HashMap<>();

    private static WindowBuilder intance = new WindowBuilder();

    public static WindowBuilder getIntance() {
        return intance;
    }

    private WindowBuilder() {

        createWindow(WindowStorage.addMaterialWindow);
        createWindow(WindowStorage.showMaterialWindow);
        createWindow(WindowStorage.updateMaterialWindow);
        createWindow(WindowStorage.deleteMaterialWindow);
        createWindow(WindowStorage.addRecipeWindow);
        createWindow(WindowStorage.showRecipeWindow);
        createWindow(WindowStorage.addProductWindow);
        createWindow(WindowStorage.showProductWindow);
        createWindow(WindowStorage.addTruckWindow);
        createWindow(WindowStorage.showTruckWindow);
        createWindow(WindowStorage.addStaffWindow);
        createWindow(WindowStorage.showStaffWindow);
        createWindow(WindowStorage.updateRecipeWindow);
        createWindow(WindowStorage.updateProductWindow);
        createWindow(WindowStorage.updateStaffWindow);
        createWindow(WindowStorage.updateTruckWindow);
        createWindow(WindowStorage.deleteRecipeWindow);
        createWindow(WindowStorage.deleteProductWindow);
        createWindow(WindowStorage.deleteStaffWindow);
        createWindow(WindowStorage.deleteTruckWindow);
    }

    private void createWindow(WindowStorage windowStorage) {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(windowStorage.getXmlUi()));

        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new CanNotCreateWindowException(windowStorage, e);
        }

        stage.setTitle(windowStorage.getTitle());

        if (windowStorage.isModalWindow()) {
            stage.initModality(Modality.APPLICATION_MODAL);
        }

        Scene scene = new Scene(root, windowStorage.getWidth(), windowStorage.getHeight());
        stage.setScene(scene);
        allWindowsList.put(windowStorage,stage);
    }

    private void createWindow(Stage stage, WindowStorage windowStorage) {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(windowStorage.getXmlUi()));

        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new CanNotCreateWindowException(windowStorage, e);
        }

        stage.setTitle(windowStorage.getTitle());

        if (windowStorage.isModalWindow()) {
            stage.initModality(Modality.APPLICATION_MODAL);
        }

        Scene scene = new Scene(root, windowStorage.getWidth(), windowStorage.getHeight());
        stage.setScene(scene);
        allWindowsList.put(windowStorage,stage);
    }


    public void addMainWindow(Stage primaryStage) {
        createWindow(primaryStage, WindowStorage.mainWindow);
    }


    public void showWindow(WindowStorage winName) {
        Stage stage = allWindowsList.get(winName);
        if (stage == null){
            throw new UnknownWindowException(winName);
        }
        stage.show();
    }

    public void closeWindow(WindowStorage winName) {
        Stage stage = allWindowsList.get(winName);
        if (stage == null){
            throw new UnknownWindowException(winName);
        }
        stage.close();
    }

}
