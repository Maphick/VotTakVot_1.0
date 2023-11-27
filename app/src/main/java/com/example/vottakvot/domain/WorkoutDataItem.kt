package com.example.vottakvot.domain

data class WorkoutDataItem(
    val id: Int = 0,
    var title: String = "Здоровая спина",
    var time: Int = 7,
    var bodyType: BodyType = BodyType.FULL_BODY,
    //var body_part: String = "Всё тело",
    var type: Int = 0,
    var isAddedToMyTrainList: Boolean = false,
    var isAddedToFavourite: Boolean = false,
    var isPlaying: Boolean = false,
    var description: String = "Тренировка “Здоровая спина” помогает укрепить мышцы спины, снять напряжение и боль в области спины.",
    var contraindications: String = "Данная тренировка противопоказана беременным.",
    var exersises: MutableList<ExerciseDataItem> = mutableListOf( ExerciseDataItem(), ExerciseDataItem(), ExerciseDataItem(), ExerciseDataItem())

) {

}



enum class BodyType {
    FULL_BODY, UPPER_BODY,  BOTTOM_BODY, ABD
}