package com.example.vottakvot.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.vottakvot.R

@Entity(tableName = "exercise_table")
data class ExerciseDataItem(
    @PrimaryKey(autoGenerate = false)
    var id: String = "0",
    //var exerciseId: Int = 0,
    var workoutId: String = "0",
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
    var url: String =
    "https://v2.exercisedb.io/image/r8BjjZyCmZtkLG",
    //"https://v2.exercisedb.io/image/ozgMekY0bIcGND",
        //"https://drive.google.com/file/d/1DoZZwB61s8cXTRNh6gFe8Un5LaNqu_8P/view?usp=sharing",
        //"https://v2.exercisedb.io/image/ozgMekY0bIcGND",
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

