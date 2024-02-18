package com.example.vottakvot.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Insert
import androidx.room.Transaction
import com.example.vottakvot.domain.Exercise
import com.example.vottakvot.domain.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WorkoutRepository(private val workoutDao: WorkoutDao) {
    fun getAllItems(): Flow<List<WorkoutDataItem>> {
        /*val items = userDao.getAllusers().value!!.size
        if (items == 0) {
            this.insertUser(
                UserDataItem(
                    id = 0,
                    name = "DEMO"
                )
            )
        }*/
        return workoutDao.getAllWorkouts()
    }

    fun getAllExercises(): Flow<List<ExerciseDataItem>> {
        return workoutDao.getAllExercises()
    }

    fun getExerciseListByWorkoutId(id: Int): Flow<List<ExerciseDataItem>> {
        //withContext(Dispatchers.IO) {
        return workoutDao.getExerciseListByWorkoutId(id)
        //}
    }

    suspend fun insertWorkout(workout: WorkoutDataItem) {
        withContext(Dispatchers.IO) {
            workoutDao.insertWorkout(workout)
        }
    }

    //@Insert
    suspend fun insertExercise(exercise: ExerciseDataItem) {
        withContext(Dispatchers.IO) {
            workoutDao.insertExercise(exercise)
        }
    }


    @Transaction
    suspend fun insertWorkoutWithExercise(workout: WorkoutDataItem) {
        withContext(Dispatchers.IO) {
            val exerciseList = workout.exerciseList
            //.map { it.copy(userId = workout.id) }
            for (exercise in exerciseList) {
                // вставка тренировок с новым id
                exercise.workoutId = workout.id
                insertExercise(exercise)
            }
            //insertExerciseList(exercises)
            insertWorkout(workout)
        }
    }


    suspend fun updateExercise(exercise: ExerciseDataItem) {
        withContext(Dispatchers.IO) {
            workoutDao.updateExercise(exercise)
        }
    }

    suspend fun updateWorkout(workout: WorkoutDataItem) {
        withContext(Dispatchers.IO) {
            workoutDao.updateWorkout(workout)
        }
    }

    suspend fun deleteWorkout(id: String) {
        withContext(Dispatchers.IO) {
            workoutDao.removeWorkout(id)
        }
    }


    suspend fun deleteExercise(id: String) {
        withContext(Dispatchers.IO) {
            workoutDao.removeExercise(id)
        }
    }

}

