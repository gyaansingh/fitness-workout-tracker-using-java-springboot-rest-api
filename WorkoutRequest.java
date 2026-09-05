package com.example.fitness.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class WorkoutRequest {

    @NotBlank(message = "Workout name is required")
    private String name;

    @NotBlank(message = "Workout type is required")
    private String type;

    @NotNull(message = "Workout date is required")
    private LocalDate workoutDate;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be greater than 0")
    private Integer durationMinutes;

    @Valid
    private List<ExerciseRequest> exercises;

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

    public List<ExerciseRequest> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseRequest> exercises) {
        this.exercises = exercises;
    }

    public static class ExerciseRequest {

        @NotBlank(message = "Exercise name is required")
        private String name;

        @NotNull
        @Min(1)
        private Integer sets;

        @NotNull
        @Min(1)
        private Integer reps;

        private java.math.BigDecimal weightKg;

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

        public java.math.BigDecimal getWeightKg() {
            return weightKg;
        }

        public void setWeightKg(java.math.BigDecimal weightKg) {
            this.weightKg = weightKg;
        }
    }
}
