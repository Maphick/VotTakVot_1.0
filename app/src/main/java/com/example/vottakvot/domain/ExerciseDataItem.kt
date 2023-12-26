package com.example.vottakvot.domain

import com.example.vottakvot.R

data class ExerciseDataItem (
    val id: Int = 0,
    var title: String = "Здоровая спина",
    var time: Int = 7,
    var bodyType: BodyType = BodyType.FULL_BODY,
    var type: Int = 0,
    var approaches: Int = 1, // число подходов
    var repetitions: Int = 10,// количество повторений
    var isAddedToMyTrainList: Boolean = false,
    var isAddedToFavourite: Boolean = false,
    var isPlaying: Boolean = false,
    var img: Int = R.drawable.ex,
    var url: String = "url",
    var description: String = "description",
    var instructions: MutableList<String> = mutableListOf("Тренировка “Здоровая спина” помогает укрепить мышцы спины, снять напряжение и боль в области спины.", "step_1", "step_2", "step_3"),
    var contraindications: String = "Данная тренировка противопоказана беременным."
)

