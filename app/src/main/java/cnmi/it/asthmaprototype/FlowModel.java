package cnmi.it.asthmaprototype;

public class FlowModel {
    private int id;
    private int pef;
    private int userid;

    public FlowModel(int id, int pef, int userid) {
        this.id = id;
        this.pef = pef;
        this.userid = userid;
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
