package org.lesson6;

public class User {

    private String email;
    private String name;
    private String birthDate;
    private int gender;


    public User(String email, String name, String birthDate, int gender) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
