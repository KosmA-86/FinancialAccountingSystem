package myApp.domain;

/**
 * Класс Пользователи.
 */
public class User {

    private static final String PREF = "us";
    private static final String PATH = "data/Users.txt";
    private String idUser;
    private String name;
    private String surname;

    public String getPref() {
        return PREF;
    }

    public String getPath() {
        return PATH;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
