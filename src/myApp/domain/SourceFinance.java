package myApp.domain;

/**
 * Родительский класс для всех источников, типа кошелька, кредитной карты, дебитовых карт
 */
public class SourceFinance {

    private static final String PREF = "sd";
    private static final String PATH = "data/SourceFinance.txt";
    public String id;
    public String idUser;
    public String name;
    public double sum;

    public String getPref() {
        return PREF;
    }

    public String getPath() {
        return PATH;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
