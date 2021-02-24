package cnmi.it.asthmaprototype;

public class UserModel {
    private int id;
    private int age;
    private int height;
    private String gender;

    public UserModel(int id, int age, int height, String gender) {
        this.id = id;
        this.age = age;
        this.height = height;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
