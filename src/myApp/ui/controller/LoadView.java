package myApp.ui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Класс загрузки интерфейса
 */
public class LoadView {

    public void load(String path) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(path));
        root.getStylesheets().add("myApp/ui/css/Interface_css.css");
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Система учета финансов");
        newStage.show();
    }
}
