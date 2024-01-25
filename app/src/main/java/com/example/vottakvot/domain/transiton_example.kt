package com.example.vottakvot.domain

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Transaction

@Entity
data class Workout(
    @PrimaryKey
    val id: Int,

    @Ignore
    var exerciseList: List<Exercise>
)


@Entity
data class Exercise(
    @PrimaryKey
    val id: Int,
    val workoutId: Int,
    val name: String
)


@Dao
interface WorkoutDao {
    @Insert
    fun insertWorkout(workout: Workout)

    @Insert
    fun insertExercise(exercise: Exercise)
    {
        insertExercise(exercise)
    }

    @Insert
    fun insertExerciseList(exercises: List<Exercise>)
    {
        for (exercise in exercises)
        {
            insertExercise(exercise)
        }
    }

    @Query("SELECT * FROM Workout WHERE id =:id")
    fun getWorkout(id: Int): Workout

    @Query("SELECT * FROM Exercise WHERE workoutId =:workoutId")
    fun getExerciseList(workoutId: Int): List<Exercise>

    @Transaction
    fun insertWorkoutWithExercise(workout: Workout) {
        val workouts = workout.exerciseList.map { it.copy(workoutId = workout.id) }
        insertExerciseList(workouts)
        insertWorkout(workout)
    }

    @Transaction
    fun getWorkoutWithExercise(id: Int): Workout {
        val workout = getWorkout(id)
        val exercises = getExerciseList(id)
        workout.exerciseList = exercises
        return workout
    }
}