package com.example.fitzoneadmin;

public class WorkoutItem {
    private String workoutName;
    private String workoutFocusArea;
    private String workoutImageResourceId;
    private String workoutDescription;
    public WorkoutItem(String workoutName, String workoutFocusArea, String workoutImageResourceId) {
        this.workoutName = workoutName;
        this.workoutFocusArea = workoutFocusArea;
        this.workoutImageResourceId = workoutImageResourceId;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

    public WorkoutItem(String workoutName, String workoutFocusArea, String workoutDescription, String workoutImageResourceId) {
        this.workoutName = workoutName;
        this.workoutFocusArea = workoutFocusArea;
        this.workoutImageResourceId = workoutImageResourceId;
        this.workoutDescription=workoutDescription;
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

    public String getWorkoutImageResourceId() {
        return workoutImageResourceId;
    }

    public void setWorkoutImageResourceId(String workoutImageResourceId) {
        this.workoutImageResourceId = workoutImageResourceId;
    }

    public WorkoutItem(String workoutName, String workoutFocusArea) {
        this.workoutName=workoutName;
        this.workoutFocusArea=workoutFocusArea;
        // Default constructor required for Firebase
    }
}
