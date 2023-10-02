package com.example.fitzoneadmin;

public class TrainerItem {
    private String trainerName;
    private int trainerImageResourceId;

    public TrainerItem(String trainerName, int trainerImageResourceId) {
        this.trainerName = trainerName;
        this.trainerImageResourceId = trainerImageResourceId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public int getTrainerImageResourceId() {
        return trainerImageResourceId;
    }
}
