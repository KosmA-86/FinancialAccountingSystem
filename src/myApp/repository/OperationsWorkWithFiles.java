package myApp.repository;

import myApp.domain.Operation;
import myApp.domain.TypeOperation;
import myApp.domain.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс по работе с файлами для оперций, запись в файл, считывание из файла и т.д.
 */

public class OperationsWorkWithFiles {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public ArrayList<Operation> readAllOperation(String path, User currentUser, String idSource,
                                                 TypeOperation typeOperation) throws IOException {

        File f = new File(path);
        List<String> allRecords;
        ArrayList<Operation> allRecordsOperation = new ArrayList<>();
        if (f.exists()) {
            Stream<String> linesFromFiles = Files.lines(Paths.get(path));
            if (typeOperation == TypeOperation.ALL) {
                allRecords = linesFromFiles
                        .filter((s) -> s.contains(currentUser.getIdUser()))
                        .filter((s) -> s.contains(idSource))
                        .collect(Collectors.toList());
            } else {
                allRecords = linesFromFiles
                        .filter((s) -> s.contains(currentUser.getIdUser()))
                        .filter((s) -> s.contains(idSource))
                        .filter((s) -> s.contains(typeOperation.toString()))
                        .collect(Collectors.toList());
            }
            for (String rec : allRecords) {
                String id = rec.substring((rec.indexOf("id:") + 3), rec.indexOf(";"));
                String name = rec.substring((rec.indexOf("name:") + 5), rec.indexOf(";",
                        (rec.indexOf("name:") + 5)));
                String sum = rec.substring((rec.indexOf("sum:") + 4), rec.indexOf(";",
                        (rec.indexOf("sum:") + 4)));
                String date = rec.substring((rec.indexOf("date:") + 5), rec.indexOf(";",
                        (rec.indexOf("date:") + 5)));
                String tOperation = rec.substring((rec.indexOf("type:") + 5), rec.indexOf(";",
                        (rec.indexOf("type:") + 5)));
                Operation operation = new Operation();
                operation.setId(id);
                operation.setIdSource(idSource);
                operation.setName(name);
                operation.setSum(Double.parseDouble(sum));
                operation.setIdUser(currentUser.getIdUser());
                operation.setDateOperation(date);
                if (tOperation.equals("CREDIT")) {
                    operation.setTypeOperation(TypeOperation.CREDIT);
                } else if (tOperation.equals("DEBIT")) {
                    operation.setTypeOperation(TypeOperation.DEBIT);
                } else {
                    operation.setTypeOperation(TypeOperation.ALL);
                }
                allRecordsOperation.add(operation);
            }
        }
        return allRecordsOperation;
    }

    public void addInFileOperations(LocalDate date, String idOperation, String idSource, String idUser, String name,
                                    double sum) throws IOException {
        String typeOperation = "";
        if (sum < 0) {
            typeOperation = TypeOperation.CREDIT.toString();
        } else
            typeOperation = TypeOperation.DEBIT.toString();
        String walletData = "id:" + idOperation + ";name:" + name + ";source:" + idSource +
                ";sum:" + sum + ";idUser:" + idUser + ";type:" + typeOperation + ";date:" +
                date.format(formatter) + "; ";
        File file = new File(new Operation().getPath());
        if (file.exists()) {
            FileWriter fw = new FileWriter(new Operation().getPath(), true);
            fw.write(walletData);
            fw.append("\n");
            fw.close();
        } else {
            file.createNewFile();
            FileWriter fw = new FileWriter(new Operation().getPath(), true);
            fw.write(walletData);
            fw.append("\n");
            fw.close();
        }
    }
}
