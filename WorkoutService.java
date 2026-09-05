package com.example.fitness.service;

import com.example.fitness.dto.WorkoutRequest;
import com.example.fitness.exception.ResourceNotFoundException;
import com.example.fitness.model.Workout;
import com.example.fitness.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout getWorkoutById(Long id) {

        Workout workout = workoutRepository.findById(id);

        if (workout == null) {
            throw new ResourceNotFoundException(
                    "Workout not found with id: " + id
            );
        }

        return workout;
    }

    @Transactional
    public Workout createWorkout(WorkoutRequest request) {

        Long workoutId =
                workoutRepository.saveWorkout(request);

        if (request.getExercises() != null) {

            for (WorkoutRequest.ExerciseRequest exercise
                    : request.getExercises()) {

                workoutRepository.saveExercise(
                        workoutId,
                        exercise
                );
            }
        }

        return getWorkoutById(workoutId);
    }

    @Transactional
    public Workout updateWorkout(
            Long id,
            WorkoutRequest request) {

        getWorkoutById(id);

        workoutRepository.updateWorkout(id, request);

        workoutRepository.deleteExercises(id);

        if (request.getExercises() != null) {

            for (WorkoutRequest.ExerciseRequest exercise
                    : request.getExercises()) {

                workoutRepository.saveExercise(
                        id,
                        exercise
                );
            }
        }

        return getWorkoutById(id);
    }

    @Transactional
    public void deleteWorkout(Long id) {

        getWorkoutById(id);

        workoutRepository.deleteExercises(id);
        workoutRepository.deleteWorkout(id);
    }

    public List<Workout> getByType(String type) {
        return workoutRepository.findByType(type);
    }

    public List<Workout> getByDate(LocalDate date) {
        return workoutRepository.findByDate(date);
    }
}
