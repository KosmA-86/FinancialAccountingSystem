package myApp.repository;

import myApp.domain.CreditCard;
import myApp.domain.User;

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
 * Класс работы с файлами по кредитным картам: запись в файл, считывание из файла и т.д.
 */
public class CreditCardWorkWithFiles {

    public ArrayList<CreditCard> readAllCC(String path, User currentUser) throws IOException {

        File f = new File(path);
        List<String> allRecords;
        ArrayList<CreditCard> allRecordsCC = new ArrayList<>();
        if (f.exists()) {
            Stream<String> linesFromFiles = Files.lines(Paths.get(path));
            allRecords = linesFromFiles.filter((s) ->
                    s.contains(currentUser.getIdUser())).collect(Collectors.toList());
            for (String rec : allRecords) {
                String id = rec.substring((rec.indexOf("id:") + 3), rec.indexOf(";"));
                String name = rec.substring((rec.indexOf("name:") + 5), rec.indexOf(";", (rec.indexOf("name:") + 5)));
                String sum = rec.substring((rec.indexOf("sum:") + 4), rec.indexOf(";", (rec.indexOf("sum:") + 4)));
                CreditCard creditCard = new CreditCard();
                creditCard.setId(id);
                creditCard.setName(name);
                creditCard.setSum(Double.parseDouble(sum));
                creditCard.setIdUser(currentUser.getIdUser());
                allRecordsCC.add(creditCard);
            }
        }
        return allRecordsCC;
    }

    public void addInFileCreditCard(String idCreditCard, String idUser, String nameCrCard, double sumCrCard,
                                    double limitCrCard, double percentCrCard) throws IOException {

        String CrCardData = "id:" + idCreditCard + ";name:" + nameCrCard + ";sum:" + sumCrCard + ";userId:"
                + idUser + ";limit:" + limitCrCard + ";percent:" + percentCrCard + "; ";

        File file = new File(new CreditCard().getPath());
        if (new OtherWorkWithFiles().findFile(new CreditCard().getPath())) {
            FileWriter fw = new FileWriter(new CreditCard().getPath(), true);
            fw.write(CrCardData);
            fw.append("\n");
            fw.close();
        } else {
            file.createNewFile();
            FileWriter fw = new FileWriter(new CreditCard().getPath(), true);
            fw.write(CrCardData);
            fw.append("\n");
            fw.close();
        }
    }
}
