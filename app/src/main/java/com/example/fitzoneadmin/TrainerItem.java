package com.example.fitzoneadmin;
public class TrainerItem {
    private String trainerName,email,number,age,gender;
    private String trainerImageResourceId;



    public String getEmail() {
        return email;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getTrainerImageResourceId() {
        return trainerImageResourceId;
    }

    public void setTrainerImageResourceId(String trainerImageResourceId) {
        this.trainerImageResourceId = trainerImageResourceId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



}