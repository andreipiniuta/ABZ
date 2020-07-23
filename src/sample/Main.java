package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    public Main() throws IOException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        WindowBuilder wb = WindowBuilder.getIntance();
        wb.addMainWindow(primaryStage);
        wb.showWindow(WindowStorage.mainWindow);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
