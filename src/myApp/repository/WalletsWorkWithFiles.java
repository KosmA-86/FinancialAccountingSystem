package myApp.repository;

import myApp.domain.User;
import myApp.domain.Wallet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Класс работы с файлами по кошелькам: запись в файл, считывание из файла и т.д.
 */
public class WalletsWorkWithFiles {

    public ArrayList<Wallet> readAllWallet(String path, User currentUser) throws IOException {

        File f = new File(path);
        List<String> allRecords;
        ArrayList<Wallet> allRecordsWallet = new ArrayList<>();
        if (f.exists()) {
            Stream<String> linesFromFiles = Files.lines(Paths.get(path));
            allRecords = linesFromFiles.filter((s) ->
                    s.contains(currentUser.getIdUser())).collect(Collectors.toList());
            for (String rec : allRecords) {
                String id = rec.substring((rec.indexOf("id:") + 3), rec.indexOf(";"));
                String name = rec.substring((rec.indexOf("name:") + 5), rec.indexOf(";", (rec.indexOf("name:") + 5)));
                String sum = rec.substring((rec.indexOf("sum:") + 4), rec.indexOf(";", (rec.indexOf("sum:") + 4)));
                Wallet wallet = new Wallet();
                wallet.setId(id);
                wallet.setName(name);
                wallet.setSum(Double.parseDouble(sum));
                wallet.setIdUser(currentUser.getIdUser());
                allRecordsWallet.add(wallet);
            }
        }
        return allRecordsWallet;
    }

    public void addInFileWallet(String idWallet, String idUser, String nameWallet, double sumWallet) throws IOException {

        String walletData = "id:" + idWallet + ";name:" + nameWallet + ";sum:" + sumWallet + ";idUser:" + idUser + "; ";

        File file = new File(new Wallet().getPath());
        if (file.exists()) {
            FileWriter fw = new FileWriter(new Wallet().getPath(), true);
            fw.write(walletData);
            fw.append("\n");
            fw.close();
        } else {
            file.createNewFile();
            FileWriter fw = new FileWriter(new Wallet().getPath(), true);
            fw.write(walletData);
            fw.append("\n");
            fw.close();
        }
    }

    public void calcSumAllWallets(String idWallet, String idUser, String nameWallet, double sumWallet) throws IOException {

        String walletData = "id:" + idWallet + "; name:" + nameWallet + "; sum:" + sumWallet + "; idUser:" + idUser + ";";

        File file = new File("Wallet.txt");
        if (file.exists()) {
            FileWriter fw = new FileWriter("Wallet.txt", true);
            fw.write(walletData);
            fw.append("\n");
            fw.close();
        } else {
            file.createNewFile();
            FileWriter fw = new FileWriter("Wallet.txt", true);
            fw.write(walletData);
            fw.append("\n");
            fw.close();
        }
    }
}
