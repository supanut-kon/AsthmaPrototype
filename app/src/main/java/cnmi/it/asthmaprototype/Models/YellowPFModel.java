package cnmi.it.asthmaprototype.Models;

public class YellowPFModel {
    private int id;
    private int pef;
    private String zone;
    private int pefr_max;
    private int percent80;
    private int percent60;
    private int patientid;
    private String date;
    private String time;
    private String enddate;
    private int isactive;

    public YellowPFModel(int id, int pef, String zone, int pefr_max, int percent80, int percent60, int patientid, String date, String time, String enddate, int isactive){
        this.id = id;
        this.pef = pef;
        this.zone = zone;
        this.pefr_max = pefr_max;
        this.percent80 = percent80;
        this.percent60 = percent60;
        this.patientid = patientid;
        this.date = date;
        this.time = time;
        this.enddate = enddate;
        this.isactive = isactive;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPef() {
        return pef;
    }

    public void setPef(int pef) {
        this.pef = pef;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getPefr_max() {
        return pefr_max;
    }

    public void setPefr_max(int pefr_max) {
        this.pefr_max = pefr_max;
    }

    public int getPercent80() {
        return percent80;
    }

    public void setPercent80(int percent80) {
        this.percent80 = percent80;
    }

    public int getPercent60() {
        return percent60;
    }

    public void setPercent60(int percent60) {
        this.percent60 = percent60;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }
}
