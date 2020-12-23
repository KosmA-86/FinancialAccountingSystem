package myApp.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import myApp.service.UserService;

import java.io.IOException;

/**
 * Класс добавляет новых пользователей
 */
public class AddUserController {

    @FXML
    private Button btnOK;
    @FXML
    private TextField userName;
    @FXML
    private TextField userSurname;

    private UserService userService = new UserService();

    @FXML
    public void clickOK() throws IOException {
        //закрываем предыдущее окно
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();

        //если необходимые данные заполнены, заносим их в файл
        if ((!userName.getText().equals("")) && (!userSurname.getText().equals(""))) {
            userService.addNewUser(userName.getText(), userSurname.getText());
        }
        //после добавления подгружаем интерфейс
        new LoadView().load("myApp/ui/view/Main.fxml");
    }
}