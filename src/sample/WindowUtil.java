package sample;

import javafx.scene.control.TextField;

public class WindowUtil {
    public static double readDouble(TextField f){
        String text = f.getText();
        try {
            Double value = Double.parseDouble(text);
            return value;
        } catch (NumberFormatException e) {
            throw new RuntimeException();
        }
    }
}
