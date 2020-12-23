package myApp.repository;

import myApp.domain.Operation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
/**
 * Класс по работе с файлами содержащий различные общие операции для всех сущностей: найти ид, удалить строку и т.д.
 */

public class OtherWorkWithFiles {

    private Operation operation = new Operation();

    public String findId(String path, String pref) throws IOException {
        String idObject;
        int id = 0;

        if (findFile(path)) {
            String lastLine;

            Stream<String> countLineFromFiles = Files.lines(Paths.get(path));
            //находим количество строк в файле
            long count = countLineFromFiles.count() - 1;
            if (count < 0) {
                count = 0;
            }

            Stream<String> lastLineFromFiles = Files.lines(Paths.get(path));
            //находим последний элемент в файле
            lastLine = lastLineFromFiles.skip(count).findAny().orElse("");

            if (!lastLine.equals("")) {
                id = Integer.parseInt(lastLine.substring(3 + pref.length(), lastLine.indexOf(";")));
            }

        }
        id += 1;
        idObject = pref + id;
        return idObject;
    }

    public boolean findFile(String path) {
        File f = new File(path);
        return f.exists();
    }

    public void delFileString(String path, String id) throws IOException {

        BufferedReader brSourceFinance = new BufferedReader(new FileReader(path));
        StringBuffer sb = new StringBuffer();
        String stringSourceFinance;
        while ((stringSourceFinance = brSourceFinance.readLine()) != null) {
            if (stringSourceFinance.contains(id)) {
                continue;
            }
            sb.append(stringSourceFinance).append("\n");
        }
        brSourceFinance.close();
        FileWriter fw = new FileWriter(path);
        fw.write(sb.toString());
        fw.close();
    }

    public double calcAmountSource(String id, String path) throws IOException {
        // ищем изначальную сумму источника(кошелька, кредитной карты и т.д.)
        BufferedReader brAmountSource = new BufferedReader(new FileReader(path));
        String strAmount;
        double amountSource = 0;
        while ((strAmount = brAmountSource.readLine()) != null) {
            if (strAmount.contains(id)) {
                amountSource += Double.parseDouble(strAmount.substring((strAmount.indexOf("sum:") + 4),
                        strAmount.indexOf(";", (strAmount.indexOf("sum") + 4))));
            }
        }
        brAmountSource.close();
        // ищем сумму операций по источнику
        BufferedReader brAmountOperation = new BufferedReader(new FileReader(operation.getPath()));
        double amountOperation = 0;
        while ((strAmount = brAmountOperation.readLine()) != null) {
            if (strAmount.contains(id)) {
                amountOperation += Double.parseDouble(strAmount.substring((strAmount.indexOf("sum:") + 4),
                        strAmount.indexOf(";", (strAmount.indexOf("sum") + 4))));
            }
        }
        brAmountOperation.close();
        return amountOperation + amountSource;
    }

    public double calcTotalAmountSource(String path) throws IOException {
        // ищем изначальную сумму источника(кошелька, кредитной карты и т.д.)
        BufferedReader brAmountSource = new BufferedReader(new FileReader(path));
        String strAmount;
        double amountSource = 0;
        while ((strAmount = brAmountSource.readLine()) != null) {
            amountSource += Double.parseDouble(strAmount.substring((strAmount.indexOf("sum:") + 4),
                    strAmount.indexOf(";", (strAmount.indexOf("sum") + 4))));
        }
        brAmountSource.close();

        return amountSource;
    }
}
