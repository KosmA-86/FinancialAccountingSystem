/*
Класс стартует проект
 */

import javafx.application.Application;
import javafx.stage.Stage;
import myApp.ui.controller.LoadView;

import java.io.IOException;


public class InterfaceStart extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        //старт приложения
        new LoadView().load("myApp/ui/view/Start.fxml");
    }
}

