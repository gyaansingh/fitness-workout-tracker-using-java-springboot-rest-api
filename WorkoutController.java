package com.example.fitness.controller;

import com.example.fitness.dto.WorkoutRequest;
import com.example.fitness.model.Workout;
import com.example.fitness.service.WorkoutService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    // GET ALL
    @GetMapping
    public List<Workout> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Workout getWorkoutById(
            @PathVariable Long id) {

        return workoutService.getWorkoutById(id);
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Workout createWorkout(
            @Valid @RequestBody WorkoutRequest request) {

        return workoutService.createWorkout(request);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Workout updateWorkout(
            @PathVariable Long id,
            @Valid @RequestBody WorkoutRequest request) {

        return workoutService.updateWorkout(
                id,
                request
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout(
            @PathVariable Long id) {

        workoutService.deleteWorkout(id);
    }

    // FILTER BY TYPE
    @GetMapping("/type/{type}")
    public List<Workout> getByType(
            @PathVariable String type) {

        return workoutService.getByType(type);
    }

    // FILTER BY DATE
    @GetMapping("/date/{date}")
    public List<Workout> getByDate(
            @PathVariable LocalDate date) {

        return workoutService.getByDate(date);
    }
}
