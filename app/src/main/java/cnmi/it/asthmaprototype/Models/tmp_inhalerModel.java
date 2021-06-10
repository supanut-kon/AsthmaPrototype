package cnmi.it.asthmaprototype.Models;

public class tmp_inhalerModel {
    private int id;
    private int did;
    private String name;
    private String times;
    private String inaday;
    private int type;
    private int isactive;
    private int morning;
    private int evening;

    public tmp_inhalerModel(int id, int did, String name, String times, String inaday, int type, int isactive, int morning, int evening) {
        this.id = id;
        this.did = did;
        this.name = name;
        this.times = times;
        this.inaday = inaday;
        this.type = type;
        this.isactive = isactive;
        this.morning = morning;
        this.evening = evening;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getInaday() {
        return inaday;
    }

    public void setInaday(String inaday) {
        this.inaday = inaday;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
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
