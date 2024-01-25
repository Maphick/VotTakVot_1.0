package com.example.vottakvot.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "workout_U_table")
data class WorkoutUDataItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String = "Здоровая спина",
    var time: Int = 7,
    var bodyType: BodyType = BodyType.FULL_BODY,
    var type: Int = 0,
    var isAddedToMyTrainList: Boolean = false,
    var isAddedToFavourite: Boolean = false,
    var isPlaying: Boolean = false,
    var description: String = "Тренировка “Здоровая спина” помогает укрепить мышцы спины, снять напряжение и боль в области спины.",
    var contraindications: String = "Данная тренировка противопоказана беременным.",
    //@Ignore
    //var exersises: MutableList<ExerciseDataItem> = mutableListOf(ExerciseDataItem())
) {

}



