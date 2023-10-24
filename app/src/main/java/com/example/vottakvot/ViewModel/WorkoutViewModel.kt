package com.example.vottakvot.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkoutViewModel: ViewModel() {
    // Проигрывается ли видео
    // поле
    private var  _isPlaying = MutableLiveData<Boolean>()

    // public - переменная, на которую мы будем подписываться
    val isPlaying : LiveData<Boolean> = _isPlaying

    //  метод, который будет изменять состояние подписки
    public fun changePlayingStatus()
    {
        val wasPlaying =  _isPlaying.value ?: false
        _isPlaying.value  = !wasPlaying
    }

    // Добавлена ли тренировка в список тренировок пользователя
    private var  _isAddedToMyTrainList = MutableLiveData<Boolean>()
    val  isAddedToMyTrainList : LiveData<Boolean> = _isAddedToMyTrainList
    public fun changeAddedToMyTrainList()
    {
        val wasAdded =  _isAddedToMyTrainList.value ?: false
        _isAddedToMyTrainList.value  = !wasAdded
    }

    // Добавлена ли тренировка в избранное
    private var  _isAddedToFavourite = MutableLiveData<Boolean>()
    val  isAddedToFavourite : LiveData<Boolean> = _isAddedToFavourite
    public fun changeAddedToFavourite()
    {
        val wasAdded =  _isAddedToFavourite.value ?: false
        _isAddedToFavourite.value  = !wasAdded
    }

}