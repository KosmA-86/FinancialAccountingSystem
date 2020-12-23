package myApp.repository;

import myApp.domain.SourceFinance;

/**
 * Синголтон класс, для получения информации по источнику операции: кошелек, кредитная карта
 */
public final class SourceHolderSingleton {

    private final static SourceHolderSingleton INSTANCE = new SourceHolderSingleton();
    private SourceFinance sourceFinance;

    private SourceHolderSingleton() {
        //do nothing
    }

    public static SourceHolderSingleton getFindSource() {
        return INSTANCE;
    }

    public SourceFinance getSourceFinance() {
        return this.sourceFinance;
    }

    public void setSourceFinance(SourceFinance sourceFinance) {
        this.sourceFinance = sourceFinance;
    }
}
