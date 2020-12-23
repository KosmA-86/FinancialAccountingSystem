package myApp.service;

import myApp.domain.Operation;
import myApp.domain.Wallet;
import myApp.repository.OtherWorkWithFiles;
import myApp.repository.SourceHolderSingleton;
import myApp.repository.WalletsWorkWithFiles;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

public class WalletService {

    private WalletsWorkWithFiles walletsWorkWithFiles = new WalletsWorkWithFiles();
    private Wallet wallet = new Wallet();
    private SelectedItemService selectedItemService = new SelectedItemService();
    private OtherWorkWithFiles otherWorkWithFiles = new OtherWorkWithFiles();
    private Operation operation = new Operation();

    public void addNewWallet(String name, @NotNull String sum) throws IOException {

        if (sum.matches("\\d*")) {
            walletsWorkWithFiles.addInFileWallet(otherWorkWithFiles.findId(wallet.getPath(), wallet.getPref()),
                    selectedItemService.getSelectedUser().getIdUser(), name, Double.parseDouble(sum));
        }
    }

    public ArrayList<Wallet> loadUserWallet() throws IOException {

        ArrayList<Wallet> allRecordsWallet = walletsWorkWithFiles.readAllWallet(wallet.getPath(),
                selectedItemService.getSelectedUser());
        return allRecordsWallet;
    }

    public void delWallet() throws IOException {

        SourceHolderSingleton findSourceFinance = SourceHolderSingleton.getFindSource();
        //удаляем кошелек
        otherWorkWithFiles.delFileString(wallet.getPath(), findSourceFinance.getSourceFinance().getId());
        //удаляем операции по кошельку
        otherWorkWithFiles.delFileString(operation.getPath(), findSourceFinance.getSourceFinance().getId());
    }
}
