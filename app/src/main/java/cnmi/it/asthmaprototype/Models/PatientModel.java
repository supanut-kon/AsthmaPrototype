package cnmi.it.asthmaprototype.Models;

public class PatientModel {
    private int id;
    private String hn;
    private String name;
    private int age;
    private int height;
    private String gender;
    private String weight;
    private String congenital;


    public PatientModel(int id, String hn, String name,int age, int height, String gender, String weight, String congenital) {
        this.id = id;
        this.hn = hn;
        this.name = name;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.weight = weight;
        this.congenital = congenital;

    }

    public String getHn() {
        return hn;
    }

    public void setHn(String hn) {
        this.hn = hn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCongenital() {
        return congenital;
    }

    public void setCongenital(String congenital) {
        this.congenital = congenital;
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
