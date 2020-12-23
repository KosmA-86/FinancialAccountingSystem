package myApp.service;

import javafx.collections.ObservableList;
import myApp.domain.SourceFinance;
import myApp.repository.OtherWorkWithFiles;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
/**
 * Класс перезентор, для подсчета сумм: всего по кошелькам, всего по кредитным картам, с учетом введенных операций
 */
public class CalculationAmountItemService {

    private OtherWorkWithFiles otherWorkWithFiles = new OtherWorkWithFiles();

    public ObservableList<? extends SourceFinance> calcAmountSource
            (@NotNull ObservableList<? extends SourceFinance> sourceList) throws IOException {

        double sum;
        for (int i = 0; i < sourceList.size(); i++) {
            sum = otherWorkWithFiles.calcAmountSource(sourceList.get(i).getId(), sourceList.get(i).getPath());
            sourceList.get(i).setSum(sum);
        }
        return sourceList;
    }

    public String totalAmountSource(@NotNull ObservableList<? extends SourceFinance> sourceList) {

        double sum = 0;
        for (int i = 0; i < sourceList.size(); i++) {
            sum += sourceList.get(i).getSum();
        }
        return Double.toString(sum);
    }
}
