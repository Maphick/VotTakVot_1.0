package com.example.vottakvot.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vottakvot.domain.WorkoutDataItem

class ConfigViewModel {

    private val  _isInternetOn = MutableLiveData<Boolean>(false)

    val isInternetOn: LiveData<Boolean> = _isInternetOn

    private val  _isOnboardingPassed = MutableLiveData<Boolean>(false)

    val isOnboardingPassed: LiveData<Boolean> = _isOnboardingPassed

}