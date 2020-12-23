package myApp.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML
    private Button btnStart;

    @FXML
    public void clickStart() throws IOException {

        //Метод обрабатывает нажитие кнопки Старт на панеле
        Stage stage = (Stage) btnStart.getScene().getWindow();
        stage.close();
        //подгружаем интерфейс
        new LoadView().load("myApp/ui/view/Main.fxml");
    }
}
