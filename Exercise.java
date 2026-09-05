package com.example.fitness.model;

import java.math.BigDecimal;

public class Exercise {

    private Long id;
    private Long workoutId;
    private String name;
    private Integer sets;
    private Integer reps;
    private BigDecimal weightKg;

    public Exercise() {
    }

    public Exercise(Long id,
                    Long workoutId,
                    String name,
                    Integer sets,
                    Integer reps,
                    BigDecimal weightKg) {

        this.id = id;
        this.workoutId = workoutId;
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weightKg = weightKg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public BigDecimal getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(BigDecimal weightKg) {
        this.weightKg = weightKg;
    }
}
