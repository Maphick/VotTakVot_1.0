package com.example.vottakvot.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vottakvot.database.WorkoutDataItem


// вью модель для списка всех страниц онбординга
class OnBoardingPagesListViewModel(
    private val source: List<WorkoutDataItem>
)
: ViewModel() {


    //----------------------------------------------------------ОБЩАЯ МОДЕЛЬ СПИСКА ТРЕНИРОВОК
    // список тренировок
    private var _workoutListGeneral = MutableLiveData<List<WorkoutDataItem>>(source)
    val workoutListGeneral: LiveData<List<WorkoutDataItem>> = _workoutListGeneral
}
