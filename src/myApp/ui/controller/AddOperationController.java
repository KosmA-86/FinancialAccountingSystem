package myApp.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import myApp.service.OperationService;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Класс добавления операций по кредитным картам, кошелкам, расходы
 */
public class AddOperationController {

    private OperationService operationService = new OperationService();

    @FXML
    private TextField nameOperation;
    @FXML
    private TextField sumOperation;
    @FXML
    private Button btnOK;
    @FXML
    private DatePicker dateOperation;

    @FXML
    public void initialize() {
        //по умолчанию,в поле Дата операции, ставим текущую
        dateOperation.setValue(LocalDate.now());
    }

    public void clickOK() throws IOException {
        //Метод обрабатывает нажитие кнопки Готово на панеле
        //закрываем предыдущее окно
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();
        //получаем введеную дату
        LocalDate date = dateOperation.getValue();
        //если данные заполнены, добавляем в файл
        if ((!nameOperation.getText().equals("")) && (!sumOperation.getText().equals(""))) {
            operationService.addNewOperation(date, nameOperation.getText(), sumOperation.getText());
        }
        //после добавления подгружаем интерфейс
        new LoadView().load("myApp/ui/view/UserFinance.fxml");
    }
}
