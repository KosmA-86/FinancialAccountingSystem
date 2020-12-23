package myApp.service;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import myApp.domain.Operation;
import myApp.domain.TypeOperation;
import myApp.repository.OperationsWorkWithFiles;
import myApp.repository.OtherWorkWithFiles;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Класс перезентор оперций: добавление, удаление, фильтрация по датам, пользователям
 */
public class OperationService {

    private OperationsWorkWithFiles operationsWorkWithFiles = new OperationsWorkWithFiles();
    private Operation operation = new Operation();
    private UserService userService = new UserService();
    private OtherWorkWithFiles otherWorkWithFiles = new OtherWorkWithFiles();
    private SelectedItemService selectedItemService = new SelectedItemService();

    public void addNewOperation(LocalDate date, String name, String sum) throws IOException {

        OtherWorkWithFiles otherWorkWithFiles = new OtherWorkWithFiles();
        if (sum.matches("-?\\d*")) {
            operationsWorkWithFiles.addInFileOperations(date, otherWorkWithFiles.findId(operation.getPath(),
                    operation.getPref()), selectedItemService.getSelectedSourceFinance().getId(),
                    selectedItemService.getSelectedUser().getIdUser(), name, Double.parseDouble(sum));
        }
    }

    public ArrayList<Operation> loadUserOperation() throws IOException {
        ArrayList<Operation> allOperation = null;
        if (selectedItemService.getSelectedSourceFinance() != null) {
            allOperation = operationsWorkWithFiles.readAllOperation(operation.getPath(),
                    selectedItemService.getSelectedUser(), selectedItemService.getSelectedSourceFinance().getId(),
                    TypeOperation.ALL);
        }
        return allOperation;
    }

    public ArrayList<Operation> loadUserOperationDebit() throws IOException {
        ArrayList<Operation> allOperationDebit = null;
        if (selectedItemService.getSelectedSourceFinance() != null) {
            allOperationDebit = operationsWorkWithFiles.readAllOperation(operation.getPath(),
                    selectedItemService.getSelectedUser(), selectedItemService.getSelectedSourceFinance().getId(),
                    TypeOperation.DEBIT);
        }
        return allOperationDebit;
    }

    public ArrayList<Operation> loadUserOperationCredit() throws IOException {
        ArrayList<Operation> allOperationCredit = operationsWorkWithFiles.readAllOperation(operation.getPath(),
                selectedItemService.getSelectedUser(), selectedItemService.getSelectedSourceFinance().getId(),
                TypeOperation.CREDIT);
        return allOperationCredit;
    }

    public FilteredList<Operation> filterDateOperation(ObservableList<Operation> operations,
                                                       LocalDate dateBegin, LocalDate dateEnd) {

        FilteredList<Operation> filterOperation = new FilteredList<>(operations);

        filterOperation.predicateProperty().bind(Bindings.createObjectBinding(() -> {

            final LocalDate finalMin = dateBegin == null ? LocalDate.MIN : dateBegin;
            final LocalDate finalMax = dateEnd == null ? LocalDate.MAX : dateEnd;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return wo -> !finalMin.isAfter(LocalDate.parse(wo.getDateOperation(), formatter))
                    && !finalMax.isBefore(LocalDate.parse(wo.getDateOperation(), formatter));
        }));
        return filterOperation;
    }

    public void delOperation() throws IOException {
        //удаляем операции по кошельку
        otherWorkWithFiles.delFileString(operation.getPath(), selectedItemService.getSelectedOperation().getId());
    }

}
