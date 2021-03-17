package cnmi.it.asthmaprototype.Models;

public class FlowModel {
    private int id;
    private int pef;
    private int max;
    private int percent80;
    private int percent60;
    private int userid;
    private String date;
    private String time;

    public FlowModel(int id, int pef, int max, int percent80, int percent60, int userid, String date, String time) {
        this.id = id;
        this.pef = pef;
        this.max = max;
        this.percent80 = percent80;
        this.percent60 = percent60;
        this.userid = userid;
        this.date = date;
        this.time = time;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
