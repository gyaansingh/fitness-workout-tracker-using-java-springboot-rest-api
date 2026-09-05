package com.example.fitness.repository;

import com.example.fitness.dto.WorkoutRequest;
import com.example.fitness.model.Exercise;
import com.example.fitness.model.Workout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class WorkoutRepository {

    private final JdbcTemplate jdbcTemplate;

    public WorkoutRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Workout> findAll() {

        String sql = """
                SELECT id, name, type, workout_date, duration_minutes
                FROM workouts
                ORDER BY workout_date DESC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Workout workout = new Workout();

            workout.setId(rs.getLong("id"));
            workout.setName(rs.getString("name"));
            workout.setType(rs.getString("type"));
            workout.setWorkoutDate(
                    rs.getDate("workout_date").toLocalDate()
            );
            workout.setDurationMinutes(
                    rs.getInt("duration_minutes")
            );

            workout.setExercises(
                    findExercisesByWorkoutId(workout.getId())
            );

            return workout;
        });
    }

    public Workout findById(Long id) {

        String sql = """
                SELECT id, name, type, workout_date, duration_minutes
                FROM workouts
                WHERE id = ?
                """;

        List<Workout> workouts = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {

                    Workout workout = new Workout();

                    workout.setId(rs.getLong("id"));
                    workout.setName(rs.getString("name"));
                    workout.setType(rs.getString("type"));
                    workout.setWorkoutDate(
                            rs.getDate("workout_date").toLocalDate()
                    );
                    workout.setDurationMinutes(
                            rs.getInt("duration_minutes")
                    );

                    workout.setExercises(
                            findExercisesByWorkoutId(workout.getId())
                    );

                    return workout;
                },
                id
        );

        return workouts.isEmpty() ? null : workouts.get(0);
    }

    public List<Exercise> findExercisesByWorkoutId(Long workoutId) {

        String sql = """
                SELECT id, workout_id, name, sets, reps, weight_kg
                FROM exercises
                WHERE workout_id = ?
                ORDER BY id
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Exercise exercise = new Exercise();

            exercise.setId(rs.getLong("id"));
            exercise.setWorkoutId(rs.getLong("workout_id"));
            exercise.setName(rs.getString("name"));
            exercise.setSets(rs.getInt("sets"));
            exercise.setReps(rs.getInt("reps"));
            exercise.setWeightKg(
                    rs.getBigDecimal("weight_kg")
            );

            return exercise;

        }, workoutId);
    }

    public Long saveWorkout(WorkoutRequest request) {

        String sql = """
                INSERT INTO workouts
                (name, type, workout_date, duration_minutes)
                VALUES (?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps =
                    connection.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS
                    );

            ps.setString(1, request.getName());
            ps.setString(2, request.getType());
            ps.setDate(
                    3,
                    Date.valueOf(request.getWorkoutDate())
            );
            ps.setInt(4, request.getDurationMinutes());

            return ps;

        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void saveExercise(
            Long workoutId,
            WorkoutRequest.ExerciseRequest request) {

        String sql = """
                INSERT INTO exercises
                (workout_id, name, sets, reps, weight_kg)
                VALUES (?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                workoutId,
                request.getName(),
                request.getSets(),
                request.getReps(),
                request.getWeightKg()
        );
    }

    public void updateWorkout(
            Long id,
            WorkoutRequest request) {

        String sql = """
                UPDATE workouts
                SET name = ?,
                    type = ?,
                    workout_date = ?,
                    duration_minutes = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(
                sql,
                request.getName(),
                request.getType(),
                Date.valueOf(request.getWorkoutDate()),
                request.getDurationMinutes(),
                id
        );
    }

    public void deleteExercises(Long workoutId) {

        jdbcTemplate.update(
                "DELETE FROM exercises WHERE workout_id = ?",
                workoutId
        );
    }

    public void deleteWorkout(Long id) {

        jdbcTemplate.update(
                "DELETE FROM workouts WHERE id = ?",
                id
        );
    }

    public List<Workout> findByType(String type) {

        String sql = """
                SELECT id, name, type, workout_date, duration_minutes
                FROM workouts
                WHERE type = ?
                ORDER BY workout_date DESC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Workout workout = new Workout();

            workout.setId(rs.getLong("id"));
            workout.setName(rs.getString("name"));
            workout.setType(rs.getString("type"));
            workout.setWorkoutDate(
                    rs.getDate("workout_date").toLocalDate()
            );
            workout.setDurationMinutes(
                    rs.getInt("duration_minutes")
            );

            workout.setExercises(
                    findExercisesByWorkoutId(workout.getId())
            );

            return workout;

        }, type);
    }

    public List<Workout> findByDate(java.time.LocalDate date) {

        String sql = """
                SELECT id, name, type, workout_date, duration_minutes
                FROM workouts
                WHERE workout_date = ?
                ORDER BY id DESC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Workout workout = new Workout();

            workout.setId(rs.getLong("id"));
            workout.setName(rs.getString("name"));
            workout.setType(rs.getString("type"));
            workout.setWorkoutDate(
                    rs.getDate("workout_date").toLocalDate()
            );
            workout.setDurationMinutes(
                    rs.getInt("duration_minutes")
            );

            workout.setExercises(
                    findExercisesByWorkoutId(workout.getId())
            );

            return workout;

        }, Date.valueOf(date));
    }
}
