package com.example.vottakvot.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vottakvot.domain.ExerciseDataItem
import com.example.vottakvot.domain.StatisticItem
import com.example.vottakvot.domain.WorkoutDataItem

// общая вью модель для всех списков тренировок
class TrainListViewModel(
    private val source: List<WorkoutDataItem> = listOf()
)
    : ViewModel() {





    //----------------------------------------------------------ОБЩАЯ МОДЕЛЬ СПИСКА ТРЕНИРОВОК
    // список тренировок
    private val  _workoutListGeneral = MutableLiveData<List<WorkoutDataItem>>(source)

    val workoutListGeneral: LiveData<List<WorkoutDataItem>> = _workoutListGeneral

    // выбранная тренировка
    var currentWorkoutId = 0
    // выбранное упражнение
    var currentExerciseId = 0

    fun setNewSource(new_source: List<WorkoutDataItem>) {
        _workoutListGeneral.value = new_source
    }


    fun findWorkoutById(id: Int): WorkoutDataItem {
        val modifiedList = _workoutListGeneral .value?.toMutableList() ?: mutableListOf()
        var item =  modifiedList.find{it.id == id}
        return item!!
    }

    fun findExerciseById(idWorkout: Int, idExercise: Int): ExerciseDataItem {
        val workout = findWorkoutById(idWorkout)
        var item =  workout.exersises.find{it.id == idExercise}
        return item!!
    }

    /*
    public fun getItemByIndex(model:WorkoutDataItem, index: Int): WorkoutDataItem
    {
        val modifiedList = workoutListGeneral.value?.toMutableList() ?: mutableListOf()
        modifiedList.replaceAll{
            if(it == model) {
                it.copy(isAddedToFavourite = !it.isAddedToFavourite)
            }
            else
                it
        }
        //workoutList.value = modifiedList
        return modifiedList
    }
    */



    public fun setItemByIndex(index: Int, workoutDataItem: WorkoutDataItem)
    {
        return
    }

    // получить
    public fun makeWorkoutList(source: List<WorkoutDataItem>)
    {
        _workoutListGeneral.value = source
    }
    public fun changeLikedStatusList(model: WorkoutDataItem)
    {
        changeLikedStatus(model,  _workoutListGeneral)
    }
    public fun changeAddedStatusList(model: WorkoutDataItem)
    {
        changeAddedStatus(model,  _workoutListGeneral)
    }
    public fun changePlayingStatusList(model: WorkoutDataItem)
    {
        changePlayingStatus(model,  _workoutListGeneral)
    }



    /*

    //----------------------------------------------------------ТРЕНИРОВКИ ДЛЯ ВАС
    // список "Тренировки для Вас"
    private val _workoutListTrainsForYou = MutableLiveData<List<WorkoutDataItem>>(sourceListTrainsForYou)
    val workoutListTrainsForYou: LiveData<List<WorkoutDataItem>> = _workoutListTrainsForYou
    fun changeLikedStatusListTrainsForYou(model: WorkoutDataItem)
    {
        changeLikedStatus(model, _workoutListTrainsForYou)
    }
    fun changeAddedStatusListTrainsForYou(model: WorkoutDataItem)
    {
        changeAddedStatus(model, _workoutListTrainsForYou)
    }
    fun changePlayingtatusListTrainsForYou(model: WorkoutDataItem)
    {
        changePlayingStatus(model, _workoutListTrainsForYou)
    }
    // список "Мои тренировки"
    private val _workoutListMyTrains = MutableLiveData<List<WorkoutDataItem>>(sourceListMyTrains)
    val workoutListMyTrains: LiveData<List<WorkoutDataItem>> = _workoutListMyTrains



    //----------------------------------------------------------ПОПУЛЯРНЫЕ
    // список "Популярные"
    private val _workoutListPopular = MutableLiveData<List<WorkoutDataItem>>(sourceListPopular)
    val workoutListPopular: LiveData<List<WorkoutDataItem>> = _workoutListPopular

    // список "Избранные"
    private val _workoutListFavourite = MutableLiveData<List<WorkoutDataItem>>(sourceListFavourite)
    val workoutListFavourite: LiveData<List<WorkoutDataItem>> = _workoutListFavourite

    */





    // Общие функции
    fun changeLikedStatus(model:WorkoutDataItem, workoutList:MutableLiveData<List<WorkoutDataItem>>) {
        val modifiedList = workoutList.value?.toMutableList() ?: mutableListOf()
        modifiedList.replaceAll{
            if(it == model) {
                it.copy(isAddedToFavourite = !it.isAddedToFavourite)
            }
            else
                it
        }
        workoutList.value = modifiedList
    }

    fun changeAddedStatus(model:WorkoutDataItem, workoutList:MutableLiveData<List<WorkoutDataItem>>) {
        val modifiedList = workoutList.value?.toMutableList() ?: mutableListOf()
        modifiedList.replaceAll{
            if(it == model) {
                it.copy(isAddedToMyTrainList = !it.isAddedToMyTrainList)
            }
            else
                it
        }
        workoutList.value = modifiedList
    }
    fun changePlayingStatus(model:WorkoutDataItem, workoutList:MutableLiveData<List<WorkoutDataItem>>) {
        val modifiedList = workoutList.value?.toMutableList() ?: mutableListOf()
        modifiedList.replaceAll{
            if(it == model) {
                it.copy(isPlaying = !it.isPlaying)
            }
            else
                it
        }
        workoutList.value = modifiedList
    }

    fun updateCount(workoutItem: WorkoutDataItem, item: StatisticItem) {
        val oldPWorkouts =  _workoutListGeneral.value?.toMutableList() ?: mutableListOf()
    }

    // удаление тренировки из списка
    fun remove(workoutItem: WorkoutDataItem) {
        val oldPWorkouts =  _workoutListGeneral.value?.toMutableList() ?: mutableListOf()
        oldPWorkouts.remove(workoutItem)
        _workoutListGeneral.value = oldPWorkouts
    }
}
