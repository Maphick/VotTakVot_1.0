package com.example.vottakvot.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.vottakvot.domain.Exercise
import kotlinx.coroutines.flow.Flow

//import com.example.vottakvot.domain.Exercise
//import com.example.vottakvot.domain.Workout

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workout_table ORDER BY id ASC")
    fun getAllWorkouts(): Flow<List<WorkoutDataItem>>

    @Query("SELECT * FROM exercise_table ORDER BY id ASC")
    fun getAllExercises(): Flow<List<ExerciseDataItem>>

    @Query("SELECT * FROM exercise_table WHERE workoutId =:workoutId")
    fun getExerciseListByWorkoutId(workoutId: Int): Flow<List<ExerciseDataItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(user: WorkoutDataItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseDataItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseList(exerciseList: List<ExerciseDataItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkout(workout: WorkoutDataItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateExercise(exercise: ExerciseDataItem)

    @Query("DELETE FROM workout_table where id = :id")
    suspend fun removeWorkout(id: String)

    @Query("DELETE FROM exercise_table where id = :id")
    suspend fun removeExercise(id: String)

}

/*

@Dao
interface WorkoutDao {

    // GET ----------------------------------------------------------------

    @Query("SELECT * FROM workout_table ORDER BY id ASC")
    fun getAllWorkouts(): LiveData<List<WorkoutDataItem>>
    @Query("SELECT * FROM workout_table WHERE id =:id")
    fun getWorkout(id: Int): WorkoutDataItem
   // @Query("SELECT * FROM exercise_table WHERE workoutId =:workoutId")
    //fun getExerciseList(workoutId: Int): List<ExerciseDataItem>
   /* @Transaction
    suspend fun getWorkoutWithExercise(id: Int):WorkoutDataItem {
        val workout = getWorkout(id)
        val exercises = getExerciseList(id)
        workout.exerciseList.exersises = exercises.toMutableList()
        return workout
    }*/


    // INSERT ---------------------------------------------------------------
    @Insert
    suspend fun insertWorkout(workout: WorkoutDataItem)
    /*@Insert
    suspend fun insertExerciseList(exercises: List<ExerciseDataItem>)
    @Transaction
    suspend fun insertWorkoutWithExercise(workout: WorkoutDataItem) {
        val exercises = workout.exerciseList.exersises.map { it.copy(workoutId = workout.id) }
        insertExerciseList(exercises)
        insertWorkout(workout)
    }
*/


    // UPDATE ---------------------------------------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkout(workout: WorkoutDataItem)
    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateExerciseList(exercises: List<ExerciseDataItem>)
    @Transaction
    suspend fun updateWorkoutWithExercise(workout: WorkoutDataItem) {
        val exercises = workout.exerciseList.exersises.map { it.copy(workoutId = workout.id) }
        updateExerciseList(exercises)
        updateWorkout(workout)
    }
*/

    // DELETE ------------------------------------------------------------------
    @Query("DELETE FROM workout_table where id = :id")
    suspend fun removeWorkout(id: Int)
    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun removeExerciseList(exercises: List<ExerciseDataItem>)
    @Transaction
    suspend fun removeWorkoutWithExercise(workout: WorkoutDataItem) {
        val exercises = workout.exerciseList.exersises.map { it.copy(workoutId = workout.id) }
        removeExerciseList(exercises)
        removeWorkout(workout.id)
    }
*/

}
*/