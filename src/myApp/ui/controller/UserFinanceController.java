package myApp.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import myApp.domain.CreditCard;
import myApp.domain.Operation;
import myApp.domain.Wallet;
import myApp.service.*;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Класс контроллер для работы (добавление, удаление) с финансами пользователя: КОШЕЛЬКИ, КРЕДИТНЫЕ КАРТЫ, ДОХОДЫ, РАСХОДЫ И Т.Д.
 */
public class UserFinanceController {

    @FXML
    public Button btnDateFilterW;
    @FXML
    public DatePicker dateEndW;
    @FXML
    public DatePicker dateBeginW;
    @FXML
    private TitledPane TitPaneCreditCard;
    @FXML
    private DatePicker dateEndCC;

    @FXML
    private DatePicker dateBeginCC;
    private UserService userService = new UserService();
    private WalletService walletService = new WalletService();
    private CreditCardService creditCardService = new CreditCardService();
    private OperationService operationService = new OperationService();
    private SelectedItemService selectedItemService = new SelectedItemService();
    private CalculationAmountItemService calculationAmountItemService = new CalculationAmountItemService();

    @FXML
    private RadioButton btnWalletOperationsDebit;
    @FXML
    private RadioButton btnWalletOperationsCredit;
    @FXML
    private RadioButton btnWalletOperationsAll;
    @FXML
    private RadioButton btnCreditCardOperationsAll;
    @FXML
    private RadioButton btnCreditCardOperationsDebit;
    @FXML
    private RadioButton btnCreditCardOperationsCredit;
    @FXML
    private TitledPane TitPaneWallet;
    @FXML
    private TableView<Wallet> wallet;
    @FXML
    private TableColumn<Wallet, String> nameWallet;
    @FXML
    private TableColumn<Wallet, String> sumW;
    @FXML
    private Button btnAddWallet;
    @FXML
    private Button btnDelWallet;
    @FXML
    private Button btnAddWalletOperation;
    @FXML
    private Button btnDelWalletOperation;
    @FXML
    private TableView<Operation> walletOperations;
    @FXML
    private TableColumn<Operation, String> dateOperationWallet;
    @FXML
    private TableColumn<Operation, String> nameWalletOperationCol;
    @FXML
    private TableColumn<Operation, String> sumWalletOperationCol;
    @FXML
    private TableView<CreditCard> creditCard;
    @FXML
    private TableColumn<CreditCard, String> dateOperationCreditCard;
    @FXML
    private TableColumn<CreditCard, String> nameCreditCard;
    @FXML
    private TableColumn<CreditCard, String> sumCreditCard;
    @FXML
    private Button btnAddCreditCard;
    @FXML
    private Button btnDelCreditCard;
    @FXML
    private Button btnAddCreditCardOperation;
    @FXML
    private Button btnDelCreditCardOperation;
    @FXML
    private TableView<Operation> creditCardOperations;
    @FXML
    private TableColumn<Operation, String> nameCCOperation;
    @FXML
    private TableColumn<Operation, String> sumCCOperation;

    @FXML
    private Label nameUserLabel;

    @FXML
    public void initialize() throws IOException {
        //Заполняем лейбл на панеле
        nameUserLabel.setText(userService.getUserFio());
        //добавляем иконки на кнопки
        Image imagePlus = new Image("myApp/ui/image/plus.png");
        btnAddWallet.setGraphic(new ImageView(imagePlus));
        btnAddWalletOperation.setGraphic(new ImageView(imagePlus));
        btnAddCreditCard.setGraphic(new ImageView(imagePlus));
        btnAddCreditCardOperation.setGraphic(new ImageView(imagePlus));
        //добавляем иконки на кнопки
        Image imageMinus = new Image("myApp/ui/image/minus.png");
        btnDelWallet.setGraphic(new ImageView(imageMinus));
        btnDelWalletOperation.setGraphic(new ImageView(imageMinus));
        btnDelCreditCard.setGraphic(new ImageView(imageMinus));
        btnDelCreditCardOperation.setGraphic(new ImageView(imageMinus));

        //считываем кошельки пользователя
        ObservableList<Wallet> allWalletArray =
                FXCollections.observableArrayList(walletService.loadUserWallet());
        //заполняем колонки таблицы с кошельками
        if (allWalletArray.size() != 0) {
            //подсчет суммы остатка кошельков
            ObservableList<Wallet> allWalletSumArray =
                    (ObservableList<Wallet>) calculationAmountItemService.calcAmountSource(allWalletArray);
            //считаем сумму по всем кошелькам
            TitPaneWallet.setText("Кошельки (" +
                    calculationAmountItemService.totalAmountSource(allWalletSumArray) + ")");
            nameWallet.setCellValueFactory(new PropertyValueFactory<>("name"));
            sumW.setCellValueFactory(new PropertyValueFactory<>("sum"));
            wallet.setItems(allWalletSumArray);
            //ставим по умолчанию выбранным первый кошелек
            wallet.getSelectionModel().selectFirst();
            selectedItemService.setSelectedSourceFinance(wallet.getSelectionModel().getSelectedItem());
            //считываем операции по кошельку
            ObservableList<Operation> allWalletOperationArray = FXCollections.observableArrayList(
                    operationService.loadUserOperation());
            //заполняем операции по выбранному по умолчанию кошельку
            if (allWalletOperationArray.size() != 0) {
                dateOperationWallet.setCellValueFactory(new PropertyValueFactory<>("dateOperation"));
                nameWalletOperationCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                sumWalletOperationCol.setCellValueFactory(new PropertyValueFactory<>("sum"));
                walletOperations.setItems(allWalletOperationArray);
            }
        }
        //считываем кредитные карты пользователя
        ObservableList<CreditCard> allCCArray =
                FXCollections.observableArrayList(creditCardService.loadUserCreditCard());
        //заполняем колонки таблицы с кредитными картами
        if (allCCArray.size() != 0) {
            ObservableList<CreditCard> allCCArraySum =
                    (ObservableList<CreditCard>) calculationAmountItemService.calcAmountSource(allCCArray);
            TitPaneCreditCard.setText("Кредитные карты (" +
                    calculationAmountItemService.totalAmountSource(allCCArraySum) + ")");
            nameCreditCard.setCellValueFactory(new PropertyValueFactory<>("name"));
            sumCreditCard.setCellValueFactory(new PropertyValueFactory<>("sum"));
            creditCard.setItems(allCCArraySum);
            //ставим по умолчанию выбранным первую кредитную карту
            creditCard.getSelectionModel().selectFirst();
            selectedItemService.setSelectedSourceFinance(creditCard.getSelectionModel().getSelectedItem());
            //считываем операции по кредитной карте
            ObservableList<Operation> allCreditCardOperationArray =
                    FXCollections.observableArrayList(
                            operationService.loadUserOperation());
            //заполняем операции по выбранному по умолчанию кошельку
            if (allCreditCardOperationArray.size() != 0) {
                dateOperationCreditCard.setCellValueFactory(new PropertyValueFactory<>("dateOperation"));
                nameCCOperation.setCellValueFactory(new PropertyValueFactory<>("name"));
                sumCCOperation.setCellValueFactory(new PropertyValueFactory<>("sum"));
                creditCardOperations.setItems(allCreditCardOperationArray);
            }
        }
    }

    public void addWallet() throws IOException {

        Stage stage = (Stage) btnAddWallet.getScene().getWindow();
        stage.close();
        //подгружаем интерфейс
        new LoadView().load("myApp/ui/view/AddWallet.fxml");
    }

    public void delWallet() throws IOException {

        Stage stage = (Stage) btnDelWallet.getScene().getWindow();
        stage.close();
        //инициализация выбранного кошелька, для которого будем создавать операцию
        selectedItemService.setSelectedSourceFinance(wallet.getSelectionModel().getSelectedItem());
        walletService.delWallet();
        new LoadView().load("myApp/ui/view/UserFinance.fxml");
    }

    public void addWalletOperation() throws IOException {

        Stage stage = (Stage) btnAddWalletOperation.getScene().getWindow();
        stage.close();
        //инициализация выбранного кошелька, для которого будем создавать операцию
        selectedItemService.setSelectedSourceFinance(wallet.getSelectionModel().getSelectedItem());
        //подгружаем интерфейс
        new LoadView().load("myApp/ui/view/AddOperation.fxml");
    }

    public void delWalletOperation() throws IOException {

        Stage stage = (Stage) btnDelWalletOperation.getScene().getWindow();
        stage.close();
        //инициализация выбранного кошелька, для которого будем создавать операцию
        selectedItemService.setSelectedOperation(walletOperations.getSelectionModel().getSelectedItem());
        operationService.delOperation();
        new LoadView().load("myApp/ui/view/UserFinance.fxml");
    }

    public void addCreditCard() throws IOException {

        Stage stage = (Stage) btnAddCreditCard.getScene().getWindow();
        stage.close();
        //подгружаем интерфейс
        new LoadView().load("myApp/ui/view/AddCreditCard.fxml");
    }

    public void delCreditCard() throws IOException {

        Stage stage = (Stage) btnDelCreditCard.getScene().getWindow();
        stage.close();
        //инициализация выбранного кошелька, для которого будем создавать операцию
        selectedItemService.setSelectedSourceFinance(creditCard.getSelectionModel().getSelectedItem());
        creditCardService.delCreditCard();
        new LoadView().load("myApp/ui/view/UserFinance.fxml");
    }

    public void addCreditCardOperation() throws IOException {

        Stage stage = (Stage) btnAddCreditCardOperation.getScene().getWindow();
        stage.close();
        //инициализация выбранного кошелька, для которого будем создавать операцию
        selectedItemService.setSelectedSourceFinance(creditCard.getSelectionModel().getSelectedItem());
        //подгружаем интерфейс
        new LoadView().load("myApp/ui/view/AddOperation.fxml");
    }

    public void delCreditCardOperation() throws IOException {

        Stage stage = (Stage) btnDelCreditCard.getScene().getWindow();
        stage.close();
        //инициализация выбранного кошелька, для которого будем создавать операцию
        selectedItemService.setSelectedOperation(creditCardOperations.getSelectionModel().getSelectedItem());
        operationService.delOperation();
        new LoadView().load("myApp/ui/view/UserFinance.fxml");
    }

    public void dateFilterWallet() throws IOException {
        //фильтр по дате кошельков
        selectedItemService.setSelectedSourceFinance(wallet.getSelectionModel().getSelectedItem());
        ObservableList<Operation> allWalletOperationArray = null;
        if (btnWalletOperationsAll.isSelected()) {
            allWalletOperationArray = FXCollections.observableArrayList(operationService.loadUserOperation());
        } else if (btnWalletOperationsDebit.isSelected()) {
            allWalletOperationArray = FXCollections.observableArrayList(operationService.loadUserOperationDebit());
        } else if (btnWalletOperationsCredit.isSelected()) {
            allWalletOperationArray = FXCollections.observableArrayList(operationService.loadUserOperationCredit());
        }

        LocalDate dBegW = dateBeginW.getValue();
        LocalDate dEndW = dateEndW.getValue();

        dateBeginW.valueProperty();
        dateEndW.valueProperty();

        walletOperations.setItems(operationService.filterDateOperation(allWalletOperationArray, dBegW, dEndW));
    }

    public void dateFilterCC() throws IOException {
        //фильтр по дате кредитных карт
        selectedItemService.setSelectedSourceFinance(creditCard.getSelectionModel().getSelectedItem());
        ObservableList<Operation> allCreditCardOperationArray = null;
        if (btnCreditCardOperationsAll.isSelected()) {
            allCreditCardOperationArray = FXCollections.observableArrayList(operationService.loadUserOperation());
        } else if (btnCreditCardOperationsDebit.isSelected()) {
            allCreditCardOperationArray = FXCollections.observableArrayList(operationService.loadUserOperationDebit());
        } else if (btnCreditCardOperationsCredit.isSelected()) {
            allCreditCardOperationArray = FXCollections.observableArrayList(operationService.loadUserOperationCredit());
        }

        LocalDate dBegCC = dateBeginCC.getValue();
        LocalDate dEndCC = dateEndCC.getValue();

        dateBeginCC.valueProperty();
        dateEndCC.valueProperty();
        creditCardOperations.setItems(operationService.filterDateOperation(allCreditCardOperationArray,
                dBegCC, dEndCC));
    }

    public void walletTableMouseClicked(MouseEvent mouseEvent) {
        //переход по строкам таблицы кошельков
        LocalDate dBegW = dateBeginW.getValue();
        LocalDate dEndW = dateEndW.getValue();
        //Метод обработки события выбора строки левой кнопки мышки
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            selectedItemService.setSelectedSourceFinance(wallet.getSelectionModel().getSelectedItem());
            //получаем выбранный кошелек
            try {
                ObservableList<Operation> allWalletOperationArray = null;
                if (btnWalletOperationsAll.isSelected()) {
                    allWalletOperationArray = FXCollections.observableArrayList(operationService.loadUserOperation());
                } else if (btnWalletOperationsDebit.isSelected()) {
                    allWalletOperationArray = FXCollections.observableArrayList(
                            operationService.loadUserOperationDebit());
                } else if (btnWalletOperationsCredit.isSelected()) {
                    allWalletOperationArray = FXCollections.observableArrayList(
                            operationService.loadUserOperationCredit());
                }
                walletOperations.setItems(operationService.filterDateOperation(allWalletOperationArray, dBegW, dEndW));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mouseEvent.consume();
    }

    public void creditCardTableMouseClicked(MouseEvent mouseEvent) {
        //переход по строкам таблицы кредитных карт
        LocalDate dBegCC = dateBeginCC.getValue();
        LocalDate dEndCC = dateEndCC.getValue();
        //Метод обработки события выбора строки левой кнопки мышки
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            selectedItemService.setSelectedSourceFinance(creditCard.getSelectionModel().getSelectedItem());
            //получаем выбранный кошелек
            try {
                ObservableList<Operation> allCreditCardOperationArray = null;
                if (btnCreditCardOperationsAll.isSelected()) {
                    allCreditCardOperationArray = FXCollections.observableArrayList(
                            operationService.loadUserOperation());
                } else if (btnCreditCardOperationsDebit.isSelected()) {
                    allCreditCardOperationArray = FXCollections.observableArrayList(
                            operationService.loadUserOperationDebit());
                } else if (btnCreditCardOperationsCredit.isSelected()) {
                    allCreditCardOperationArray = FXCollections.observableArrayList(
                            operationService.loadUserOperationCredit());
                }
                creditCardOperations.setItems(operationService.filterDateOperation(allCreditCardOperationArray, dBegCC,
                        dEndCC));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mouseEvent.consume();
    }

    public void filterWalletOperationDebitCreditAll(ActionEvent actionEvent) {

        LocalDate dBegW = dateBeginW.getValue();
        LocalDate dEndW = dateEndW.getValue();

        selectedItemService.setSelectedSourceFinance(wallet.getSelectionModel().getSelectedItem());
        //получаем выбранный кошелек
        try {
            ObservableList<Operation> allWalletOperationArray = null;
            if (btnWalletOperationsAll.isSelected()) {
                allWalletOperationArray = FXCollections.observableArrayList(operationService.loadUserOperation());
            } else if (btnWalletOperationsDebit.isSelected()) {
                allWalletOperationArray = FXCollections.observableArrayList(
                        operationService.loadUserOperationDebit());
            } else if (btnWalletOperationsCredit.isSelected()) {
                allWalletOperationArray = FXCollections.observableArrayList(
                        operationService.loadUserOperationCredit());
            }
            walletOperations.setItems(operationService.filterDateOperation(allWalletOperationArray, dBegW, dEndW));
        } catch (IOException e) {
            e.printStackTrace();
        }
        actionEvent.consume();
    }

    public void filterCreditCardOperationDebitCreditAll(ActionEvent actionEvent) {

        LocalDate dBegCC = dateBeginCC.getValue();
        LocalDate dEndCC = dateEndCC.getValue();
        //получаем выбранный кошелек
        try {
            ObservableList<Operation> allCreditCardOperationArray = null;
            if (btnCreditCardOperationsAll.isSelected()) {
                allCreditCardOperationArray = FXCollections.observableArrayList(
                        operationService.loadUserOperation());
            } else if (btnCreditCardOperationsDebit.isSelected()) {
                allCreditCardOperationArray = FXCollections.observableArrayList(
                        operationService.loadUserOperationDebit());
            } else if (btnCreditCardOperationsCredit.isSelected()) {
                allCreditCardOperationArray = FXCollections.observableArrayList(
                        operationService.loadUserOperationCredit());
            }
            creditCardOperations.setItems(operationService.filterDateOperation(allCreditCardOperationArray,
                    dBegCC, dEndCC));
        } catch (IOException e) {
            e.printStackTrace();
        }
        actionEvent.consume();
    }

    public void dateFilterClearWallet(ActionEvent actionEvent) {
        //очищаем фильтры по дате в зоне кошельков
        dateBeginW.setValue(null);
        dateEndW.setValue(null);

        filterWalletOperationDebitCreditAll(actionEvent);
    }

    public void dateFilterClearCC(ActionEvent actionEvent) {
        //очищаем фильтры по дате в зоне кредитных карт
        dateBeginCC.setValue(null);
        dateEndCC.setValue(null);

        filterCreditCardOperationDebitCreditAll(actionEvent);
    }
}
