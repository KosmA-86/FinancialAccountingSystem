package myApp.service;

import myApp.domain.Operation;
import myApp.domain.SourceFinance;
import myApp.domain.User;
import myApp.repository.OperationSingleton;
import myApp.repository.SourceHolderSingleton;
import myApp.repository.UserHolderSingleton;
/**
 * Класс перезентор для установки и считывания выбранных сущностей: пользователей, кошельков, кредитных карт
 */
public class SelectedItemService {

    public User getSelectedUser() {

        UserHolderSingleton findUser = UserHolderSingleton.getFindUser();
        return findUser.getUser();
    }

    public void setSelectedUser(User user) {

        UserHolderSingleton holder = UserHolderSingleton.getFindUser();
        holder.setUser(user);
    }

    public SourceFinance getSelectedSourceFinance() {

        SourceHolderSingleton findSourceFinance = SourceHolderSingleton.getFindSource();
        return findSourceFinance.getSourceFinance();
    }

    public void setSelectedSourceFinance(SourceFinance sourceFinance) {

        SourceHolderSingleton holder = SourceHolderSingleton.getFindSource();
        holder.setSourceFinance(sourceFinance);
    }

    public Operation getSelectedOperation() {

        OperationSingleton findOperation = OperationSingleton.getFindOperation();
        return findOperation.getOperation();
    }

    public void setSelectedOperation(Operation operation) {

        OperationSingleton holder = OperationSingleton.getFindOperation();
        holder.setOperation(operation);
    }
}
