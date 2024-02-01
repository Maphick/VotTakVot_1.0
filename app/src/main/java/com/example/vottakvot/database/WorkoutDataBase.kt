package com.example.vottakvot.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

//ExerciseListConverters::class,
@TypeConverters(value = [ InstructionListConverters::class, RepetitionListConverters::class])
@Database(entities = [ExerciseDataItem::class, WorkoutDataItem::class], version =1, exportSchema = false)
abstract class WorkoutDataBase: RoomDatabase() {

    abstract fun workoutDao(): WorkoutDao

    //  static methods
    companion object {
        // singleton class
        @Volatile
        private var INSTANCE: WorkoutDataBase? = null
        final val DB_NAME: String = "workout.db"
        fun getDatabase(context: Context): WorkoutDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkoutDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}