package myApp.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import myApp.domain.User;
import myApp.service.UserService;
import myApp.service.WalletService;

import java.io.IOException;

/**
 * Класс добавления новых кошельков
 */
public class AddWalletController {

    private UserService userService = new UserService();
    private WalletService walletService = new WalletService();
    private User user;

    @FXML
    private Button btnOK;
    @FXML
    private TextField nameWallet;
    @FXML
    private TextField sumWallet;

    public void clickOK() throws IOException {
        //Метод обрабатывает нажитие кнопки Готово на панеле
        //закрываем предыдущее окно
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();
        //если необходимые данные заполнены, заносим их в файл
        if ((!nameWallet.getText().equals("")) && (!sumWallet.getText().equals(""))) {
            walletService.addNewWallet(nameWallet.getText(), sumWallet.getText());
        }
        // new UserFinanceController().refreshTable();
        //после добавления подгружаем интерфейс
        new LoadView().load("myApp/ui/view/UserFinance.fxml");
    }
}
