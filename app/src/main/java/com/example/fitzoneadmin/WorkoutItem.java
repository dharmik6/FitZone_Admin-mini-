package com.example.fitzoneadmin;

public class WorkoutItem {
    private String workoutName;
    private String workoutFocusArea;
    private String imageUrl;
    private String workoutDescription;

    public WorkoutItem(String workoutName, String workoutFocusArea, String imageUrl, String workoutDescription) {
        this.workoutName = workoutName;
        this.workoutFocusArea = workoutFocusArea;
        this.imageUrl = imageUrl;
        this.workoutDescription = workoutDescription;
    }

    public WorkoutItem(String workoutName, String workoutFocusArea, String imageUrl) {
        this.workoutName = workoutName;
        this.workoutFocusArea = workoutFocusArea;
        this.imageUrl = imageUrl;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutFocusArea() {
        return workoutFocusArea;
    }

    public void setWorkoutFocusArea(String workoutFocusArea) {
        this.workoutFocusArea = workoutFocusArea;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }
}
