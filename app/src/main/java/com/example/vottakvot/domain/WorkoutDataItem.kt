package com.example.vottakvot.domain

data class WorkoutDataItem(
    val id: Int = 0,
    var title: String = "Здоровая спина",
    var time: Int = 7,
    var body_part: String = "Всё тело",
    var type: Int = 0,
    var isAddedToMyTrainList: Boolean = false,
    var isAddedToFavourite: Boolean = false,
    var isPlaying: Boolean = false,
) {

}