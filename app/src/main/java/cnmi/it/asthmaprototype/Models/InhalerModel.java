package cnmi.it.asthmaprototype.Models;

import java.sql.Blob;

public class InhalerModel {
    private int id;
    private int did;
    private String name;
    private String times;
    private String inaday;
    private int emergency;
    private int isactive;
    public InhalerModel(int id, int did, String name, String times, String inaday, int emergency, int isactive) {
        this.id = id;
        this.did = did;
        this.name = name;
        this.times = times;
        this.inaday = inaday;
        this.emergency = emergency;
        this.isactive = isactive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getEmergency() {
        return emergency;
    }

    public void setEmergency(int emergency) {
        this.emergency = emergency;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }
}
