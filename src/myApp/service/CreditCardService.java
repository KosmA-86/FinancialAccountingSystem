package myApp.service;

import myApp.domain.CreditCard;
import myApp.domain.Operation;
import myApp.repository.CreditCardWorkWithFiles;
import myApp.repository.OtherWorkWithFiles;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Класс перезентор кредитных карт: добавление, удаление
 */
public class CreditCardService {

    private SelectedItemService selectedItemService = new SelectedItemService();
    private CreditCardWorkWithFiles creditCardWorkWithFiles = new CreditCardWorkWithFiles();
    private CreditCard creditCard = new CreditCard();
    private OtherWorkWithFiles otherWorkWithFiles = new OtherWorkWithFiles();
    private Operation operation = new Operation();

    public void addNewCreditCard(String name, String sum, String limit, String percent) throws IOException {

        OtherWorkWithFiles otherWorkWithFiles = new OtherWorkWithFiles();
        if (sum.matches("\\d*")) {
            creditCardWorkWithFiles.addInFileCreditCard(otherWorkWithFiles.findId(creditCard.getPath(),
                    creditCard.getPref()), selectedItemService.getSelectedUser().getIdUser(), name,
                    Double.parseDouble(sum), Double.parseDouble(limit), Double.parseDouble(percent));
        }
    }

    public ArrayList<CreditCard> loadUserCreditCard() throws IOException {

        ArrayList<CreditCard> allRecordsCC =
                creditCardWorkWithFiles.readAllCC(creditCard.getPath(), selectedItemService.getSelectedUser());
        return allRecordsCC;
    }

    public void delCreditCard() throws IOException {
        //удаляем кредитную карту
        otherWorkWithFiles.delFileString(creditCard.getPath(), selectedItemService.getSelectedSourceFinance().getId());
        //удаляем операции по кредитной карте
        otherWorkWithFiles.delFileString(operation.getPath(), selectedItemService.getSelectedSourceFinance().getId());
    }
}
