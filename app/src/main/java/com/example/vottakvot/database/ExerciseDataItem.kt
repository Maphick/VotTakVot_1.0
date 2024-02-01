package com.example.vottakvot.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.vottakvot.R

@Entity(tableName = "exercise_table")
data class ExerciseDataItem(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    //var exerciseId: Int = 0,
    var workoutId: Int = 0,
    var title: String = "Здоровая спина",
    var time: Int = 7,
    var bodyType: String = BodyType.FULL_BODY.toString(),
    var type: Int = 0,
    var approaches: Int = 3, // число подходов
    var repetitions: RepetitionList = RepetitionList(),// количество повторений
    var isAddedToMyTrainList: Boolean = false,
    var isAddedToFavourite: Boolean = false,
    var isPlaying: Boolean = false,
    var img: Int = R.drawable.ex,
    var url: String = "url",
    var description: String = "description",
    var instructionList: InstructionList = InstructionList(),
        //listOf("Тренировка “Здоровая спина” помогает укрепить мышцы спины, снять напряжение и боль в области спины.", "step_1", "step_2", "step_3"),
    var contraindications: String = "Данная тренировка противопоказана беременным."
)  {
}

data class InstructionList(
    var instructions: List<String> = listOf<String>()
)

data class RepetitionList(
    var repetitions: MutableList<Int> = mutableListOf<Int>(20,25,30)
)

