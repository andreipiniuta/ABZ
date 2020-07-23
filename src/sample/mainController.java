package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class mainController {

    public void addMaterial() {

        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.addMaterialWindow);
    }

    public void showMaterial() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.showMaterialWindow);
    }

    public void updateMaterial() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.updateMaterialWindow);

    }
    public void deleteMaterial() {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.deleteMaterialWindow);
    }

    public void addRecipe () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.addRecipeWindow);
    }



    public void showRecipe () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.showRecipeWindow);
    }

    public void updateRecipe () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.updateRecipeWindow);

    }

    public void deleteRecipe () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.deleteRecipeWindow);
    }

    public void addProduct () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.addProductWindow);

    }

    public void showProduct () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.showProductWindow);

    }

    public void updateProduct () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.updateProductWindow);
    }

    public void deleteProduct () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.deleteProductWindow);
    }
    public void addTruck () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.addTruckWindow);

    }

    public void showTruck () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.showTruckWindow);

    }

    public void updateTruck () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.updateTruckWindow);
    }

    public void deleteTruck () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.deleteTruckWindow);
    }

    public void addStaff () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.addStaffWindow);

    }

    public void showStaff () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.showStaffWindow);

    }

    public void updateStaff () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.updateStaffWindow);
    }

    public void deleteStaff () {
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.showWindow(WindowStorage.deleteStaffWindow);
    }
}
