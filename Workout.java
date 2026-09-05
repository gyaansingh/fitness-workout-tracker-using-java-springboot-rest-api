package com.example.fitness.model;

import java.time.LocalDate;
import java.util.List;

public class Workout {

    private Long id;
    private String name;
    private String type;
    private LocalDate workoutDate;
    private Integer durationMinutes;
    private List<Exercise> exercises;

    public Workout() {
    }

    public Workout(Long id,
                   String name,
                   String type,
                   LocalDate workoutDate,
                   Integer durationMinutes,
                   List<Exercise> exercises) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.workoutDate = workoutDate;
        this.durationMinutes = durationMinutes;
        this.exercises = exercises;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
