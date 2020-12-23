package myApp.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import myApp.domain.User;
import myApp.service.CreditCardService;
import myApp.service.UserService;

import java.io.IOException;

/**
 * Класс контроллер добавления кредитных карт
 */
public class AddCreditCardController {

    private User user;
    private UserService userService = new UserService();
    private CreditCardService creditCardService = new CreditCardService();

    @FXML
    private Button btnOK;
    @FXML
    private TextField nameCrCard;
    @FXML
    private TextField sumCrCard;
    @FXML
    private TextField limitCrCard;
    @FXML
    private TextField percentCrCard;

    public void clickOK() throws IOException {
        //Метод обрабатывает нажитие кнопки Готово на панеле
        //закрываем предыдущее окно
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();
        //если данные заполнены, добавляем в файл
        if ((!nameCrCard.getText().equals("")) && (!sumCrCard.getText().equals(""))) {
            creditCardService.addNewCreditCard(nameCrCard.getText(), sumCrCard.getText(), limitCrCard.getText(),
                    percentCrCard.getText());
        }
        //после добавления подгружаем интерфейс
        new LoadView().load("myApp/ui/view/UserFinance.fxml");
    }
}
