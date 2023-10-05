package com.example.fitzoneadmin;

public class TrainerItem {
    private String trainerName,email,gender,age,phone;
    private String trainerImageResourceId;

    public TrainerItem(String trainerName,String email) {
        this.trainerName = trainerName;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.trainerImageResourceId = trainerImageResourceId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTrainerImageResourceId() {
        return trainerImageResourceId;
    }

    public void setTrainerImageResourceId(String trainerImageResourceId) {
        this.trainerImageResourceId = trainerImageResourceId;
    }
}

