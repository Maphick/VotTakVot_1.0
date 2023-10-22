package com.example.vottakvot.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkoutViewModel: ViewModel() {
    // поле
    private var  _isFollowing = MutableLiveData<Boolean>()

    // public - переменная, на которую мы будем подписываться
    val isFollowing : LiveData<Boolean> = _isFollowing

    //  метод, который будет изменять состояние подписки
    public fun changeFollowingStatus()
    {
        val wasFollowing =  _isFollowing.value ?: false
        _isFollowing.value  = !wasFollowing
    }

}