package com.example.vottakvot.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Insert
import androidx.room.Transaction
import com.example.vottakvot.domain.Exercise
import com.example.vottakvot.domain.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutRepository(private val workoutDao: WorkoutDao) {
    fun getAllItems(): LiveData<List<WorkoutDataItem>> {
        return workoutDao.getAllWorkouts()
    }

    fun getAllExercises(): LiveData<List<ExerciseDataItem>> {
        return workoutDao.getAllExercises()
    }

    fun getExerciseListByWorkoutId(id: Int): LiveData<List<ExerciseDataItem>> {
        return workoutDao.getExerciseListByWorkoutId(id)
    }

    suspend fun insertWorkout(workout: WorkoutDataItem) {
        withContext(Dispatchers.IO) {
            workoutDao.insertWorkout(workout)
        }
    }

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

    suspend fun deleteWorkout(id: Int) {
        withContext(Dispatchers.IO) {
            workoutDao.removeWorkout(id)
        }
    }


    suspend fun deleteExercise(id: Int) {
        withContext(Dispatchers.IO) {
            workoutDao.removeExercise(id)
        }
    }

}

