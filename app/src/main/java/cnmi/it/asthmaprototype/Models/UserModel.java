package cnmi.it.asthmaprototype.Models;

public class UserModel {
    private int id;
    private String name;
    private String email;
    private String password;
    private int[] patientid;



    public UserModel() {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.patientid = patientid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int[] getPatientid() {
        return patientid;
    }

    public void setPatientid(int[] patientid) {
        this.patientid = patientid;
    }
}



