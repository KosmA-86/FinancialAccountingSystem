package myApp.domain;

/**
 * Класс Кредитные карты, наследует класс ИсточникФинансирования.
 * Наличие геттеров и сеттеров полей.
 */
public class CreditCard extends SourceFinance {

    private static final String PREF = "cc";
    private static final String PATH = "data/CreditCards.txt";
    private int limitCrCard;
    private double percentCrCard;

    public String getPref() {
        return PREF;
    }

    public String getPath() {
        return PATH;
    }
}
