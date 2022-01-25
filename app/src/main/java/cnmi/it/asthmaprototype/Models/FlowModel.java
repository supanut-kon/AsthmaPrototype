package cnmi.it.asthmaprototype.Models;

public class FlowModel {
    private int id;
    private int pefr;
    private String zone;
    private int pefr_max;
    private int percent80;
    private int percent60;
    private int patientid;
    private String date;
    private String time;
    private String haveSymptoms;
    private String[] symptoms;
    private String caremethod;

    public FlowModel(int id, int pefr, String zone, int pefr_max, int percent80, int percent60, int patientid, String date, String time, String haveSymptoms, String[] symptoms, String caremethod) {
        this.id = id;
        this.pefr = pefr;
        this.zone = zone;
        this.pefr_max = pefr_max;
        this.percent80 = percent80;
        this.percent60 = percent60;
        this.patientid = patientid;
        this.date = date;
        this.time = time;
        this.haveSymptoms = haveSymptoms;
        this.symptoms = symptoms;
        this.caremethod = caremethod;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getHaveSymptoms() {
        return haveSymptoms;
    }

    public void setHaveSymptoms(String haveSymptoms) {
        this.haveSymptoms = haveSymptoms;
    }

    public String[] getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String[] symptoms) {
        this.symptoms = symptoms;
    }

    public String getCaremethod() {
        return caremethod;
    }

    public void setCaremethod(String caremethod) {
        this.caremethod = caremethod;
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

    public int getPefr() {
        return pefr;
    }

    public void setPefr(int pefr) {
        this.pefr = pefr;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }
}
