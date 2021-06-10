package cnmi.it.asthmaprototype.Models;

public class TransactionModel {
    private int id;
    private int pid;
    private int drugid;
    private int morning;
    private int evening;

    public TransactionModel(int id, int pid, int drugid, int morning, int evening){
        this.id = id;
        this.drugid = drugid;
        this.pid = pid;
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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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
