package myApp.domain;

/**
 * Класс операций доходы и расходы по источникам
 */
public class Operation {

    private static final String PREF = "op";
    private static final String PATH = "data/Operations.txt";
    private String id;
    private String idSource;
    private String idUser;
    private String name;
    private double sum;
    private String dateOperation;
    private TypeOperation typeOperation;


    public String getPath() {
        return PATH;
    }

    public String getPref() {
        return PREF;
    }

    public String getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(String dateOperation) {
        this.dateOperation = dateOperation;
    }

    public void setIdSource(String idSource) {
        this.idSource = idSource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
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

