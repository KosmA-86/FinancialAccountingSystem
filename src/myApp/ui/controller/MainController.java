package myApp.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import myApp.domain.User;
import myApp.service.SelectedItemService;
import myApp.service.UserService;

import java.io.IOException;

/**
 * Класс контроллер, загрузки окна со списком пользователей
 */
public class MainController {

    @FXML
    private Button btnNew;
    @FXML
    private Button btnOK;
    @FXML
    private TableView<User> allUsers;
    @FXML
    private TableColumn<User, String> idColumn;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> surnameColumn;

    private UserService userService = new UserService();
    private SelectedItemService selectedItemService = new SelectedItemService();

    @FXML
    public void initialize() throws IOException {
        //Загружаем список пользователей
        ObservableList<User> allUserArray = FXCollections.observableArrayList(userService.loadAllUsers());
        if (allUserArray.size() != 0) {
            idColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            allUsers.setItems(allUserArray);
            allUsers.getSelectionModel().selectFirst();
        }
    }

    @FXML
    public void clickNew() throws IOException {
        //Метод обрабатывает нажитие кнопки Добавить на панеле
        //закрываем предыдущее окно
        Stage stage = (Stage) btnNew.getScene().getWindow();
        stage.close();
        //подгружаем интерфейс
        new LoadView().load("myApp/ui/view/AddUser.fxml");
    }

    @FXML
    public void clickOK() throws IOException {
        //Метод обрабатывает нажитие кнопки Выбрать на панеле
        //закрываем предыдущее окно
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();
        //Получаем выбранного пользователя
        selectedItemService.setSelectedUser(allUsers.getSelectionModel().getSelectedItem());
        //подгружаем интерфейс
        new LoadView().load("myApp/ui/view/UserFinance.fxml");
    }
}
