package myApp.domain;

/**
 * Класс Кошелек.
 */
public class Wallet extends SourceFinance {

    private static final String PREF = "wl";
    private static final String PATH = "data/Wallet.txt";

    public String getPref() {
        return PREF;
    }

    public String getPath() {
        return PATH;
    }
}

