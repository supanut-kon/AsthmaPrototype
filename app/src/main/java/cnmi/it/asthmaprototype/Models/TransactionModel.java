package cnmi.it.asthmaprototype.Models;

public class TransactionModel {
    private int id;
    private int drugid;
    private String drugname;
    private int morning;
    private int evening;

    public TransactionModel(int id, int drugid, String drugname, int morning, int evening){
        this.id = id;
        this.drugid = drugid;
        this.drugname = drugname;
        this.morning = morning;
        this.evening = evening;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDrugid() {
        return drugid;
    }

    public void setDrugid(int drugid) {
        this.drugid = drugid;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public int getMorning() {
        return morning;
    }

    public void setMorning(int morning) {
        this.morning = morning;
    }

    public int getEvening() {
        return evening;
    }

    public void setEvening(int evening) {
        this.evening = evening;
    }
}
