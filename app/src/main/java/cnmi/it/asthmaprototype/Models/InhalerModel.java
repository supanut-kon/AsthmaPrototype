package cnmi.it.asthmaprototype.Models;


public class InhalerModel {
    private int id;
    private int did;
    private String name;
    private String dosage;
    private int type;
    private String times;
    private String inaday;
    private int ismorning;
    private int isevening;
    private int isactive;
    public InhalerModel(int id, int did, String name, String dosage, int type, String times, String inaday, int ismorning, int isevening, int isactive) {
        this.id = id;
        this.did = did;
        this.name = name;
        this.dosage = dosage;
        this.type = type;
        this.times = times;
        this.inaday = inaday;
        this.ismorning = ismorning;
        this.isevening = isevening;
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

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
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

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsmorning() {
        return ismorning;
    }

    public void setIsmorning(int ismorning) {
        this.ismorning = ismorning;
    }

    public int getIsevening() {
        return isevening;
    }

    public void setIsevening(int isevening) {
        this.isevening = isevening;
    }
}
