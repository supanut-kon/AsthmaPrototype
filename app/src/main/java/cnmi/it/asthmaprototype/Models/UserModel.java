package cnmi.it.asthmaprototype.Models;

public class UserModel {
    private int id;
    private String name;
    private String email;
    private String password;
    private String passcode;


    public UserModel(int id, String name, String email, String password, String passcode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.passcode = passcode;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
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

}



