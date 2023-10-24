package com.example.vottakvot.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vottakvot.domain.StatisticItem
import com.example.vottakvot.domain.WorkoutDataItem

// общая вью модель для всех списков тренировок
class GeneralViewModel : ViewModel() {

    /*private val sourceList = mutableListOf<WorkoutItem>().apply {
         repeat(10) {
             add(WorkoutItem(id = it))
         }
    }*/
    private val sourceListMyTrains = mutableListOf<WorkoutDataItem>().apply {
       val workoutItem =  WorkoutDataItem(0, "Здоровая спина", 7, "Всё тело",
            0, false, false, false)
        add(workoutItem)
        add(workoutItem)
        add(workoutItem)
        add(workoutItem)
        add(workoutItem)
    }

    private val sourceListTrainsForYou= mutableListOf<WorkoutDataItem>().apply {
        val workoutItem_0 =  WorkoutDataItem(0, "Взбодрись утром!", 10, "Всё тело",
            0, false, true, false)
        add(workoutItem_0)
        val workoutItem_1 =  WorkoutDataItem(0, "Сильные руки", 11, "Вверх",
            0, false, true, false)
        add(workoutItem_1)
        val workoutItem_2 =  WorkoutDataItem(0, "Здоровая спина", 7, "Всё тело",
            0, false, true, false)
        add(workoutItem_2)
        val workoutItem_3 =  WorkoutDataItem(0, "Стальной пресс за 8 минут", 7, "Пресс",
            0, false, true, true)
        add(workoutItem_3)
        val workoutItem_4 =  WorkoutDataItem(0, "Сбросить стресс перед сном", 9, "Всё тело",
            0, false, true, true)
        add(workoutItem_4)
        val workoutItem_5 =  WorkoutDataItem(0, "Здоровые ноги", 7, "Всё тело",
            0, false, true, false)
        add(workoutItem_5)
    }
    private val sourceListPopular = mutableListOf<WorkoutDataItem>().apply {
        var workoutItem =  WorkoutDataItem(0, "Стальной пресс за 8 минут", 7, "Пресс",
            0, false, true, true)
        add(workoutItem)
        workoutItem =  WorkoutDataItem(0, "Сбросить стресс перед сном", 9, "Всё тело",
            0, false, true, true)
        add(workoutItem)
        workoutItem =  WorkoutDataItem(0, "Здоровые ноги", 7, "Всё тело",
            0, false, true, false)
        add(workoutItem)
        add(workoutItem)
        add(workoutItem)
        add(workoutItem)
        add(workoutItem)
        add(workoutItem)
    }

    private val sourceListFavourite = mutableListOf<WorkoutDataItem>().apply {
        val workoutItem =  WorkoutDataItem(0, "Здоровая спина", 7, "Всё тело",
            0, false, false, false)
        add(workoutItem)
        add(workoutItem)
        add(workoutItem)
        add(workoutItem)
        add(workoutItem)
    }

    private val sourceListSearchResult = mutableListOf<WorkoutDataItem>().apply {
        val workoutItem_0 =  WorkoutDataItem(0, "Сбросить стресс перед сном", 9, "Всё тело",
            0, false, true, false)
        add(workoutItem_0)
        val workoutItem_1 =  WorkoutDataItem(0, "Сбросить вес перед сном", 10, "Всё тело",
            0, true, true, false)
        add(workoutItem_1)
       val workoutItem_2 =  WorkoutDataItem(0, "Гимнастика для улучшения сна", 3, "Всё тело",
            0, false, true, false)
        add(workoutItem_2)
        val workoutItem_3 =  WorkoutDataItem(0, "Вечерняя растяжка", 9, "Всё тело",
            0, false, false, false)
        add(workoutItem_3)
        val workoutItem_4 =  WorkoutDataItem(0, "Йога для релаксации", 5, "Всё тело",
            0, true, false, false)
        add(workoutItem_4)
        val workoutItem_5 =  WorkoutDataItem(0, "Спокойное настроение", 6, "Всё тело",
            0, false, false, false)
        add(workoutItem_5)
        val workoutItem_6 =  WorkoutDataItem(0, "Восстановительные упражнения", 8, "Всё тело",
            0, false, false, false)
        add(workoutItem_6)
    }


    // список "Мои тренировки"
    private val _workoutListMyTrains = MutableLiveData<List<WorkoutDataItem>>(sourceListMyTrains)
    val workoutListMyTrains: LiveData<List<WorkoutDataItem>> = _workoutListMyTrains


    // список "Тренировки для Вас"
    private val _workoutListTrainsForYou = MutableLiveData<List<WorkoutDataItem>>(sourceListTrainsForYou)
    val workoutListTrainsForYou: LiveData<List<WorkoutDataItem>> = _workoutListTrainsForYou

    // список "Популярные"
    private val _workoutListPopular = MutableLiveData<List<WorkoutDataItem>>(sourceListPopular)
    val workoutListPopular: LiveData<List<WorkoutDataItem>> = _workoutListPopular

    // список "Избранные"
    private val _workoutListFavourite = MutableLiveData<List<WorkoutDataItem>>(sourceListFavourite)
    val workoutListFavourite: LiveData<List<WorkoutDataItem>> = _workoutListFavourite

    // список "Результаты поиска"
    private val _workoutListSearchResult = MutableLiveData<List<WorkoutDataItem>>(sourceListSearchResult)
    val workoutListSearchResult: LiveData<List<WorkoutDataItem>> = _workoutListSearchResult


    fun changeLikedStatusListSearchResult(model: WorkoutDataItem)
    {
        changeLikedStatus(model, _workoutListSearchResult)
    }

    fun changeAddedStatusListSearchResult(model: WorkoutDataItem)
    {
        changeAddedStatus(model, _workoutListSearchResult)
    }

    fun changePlayingtatusListSearchResult(model: WorkoutDataItem)
    {
        changePlayingStatus(model, _workoutListSearchResult)
    }

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
        val oldPWorkouts = workoutListMyTrains.value?.toMutableList() ?: mutableListOf()
    }

    // удаление тренировки из списка
    fun remove(workoutItem: WorkoutDataItem) {
        val oldPWorkouts = workoutListMyTrains.value?.toMutableList() ?: mutableListOf()
        oldPWorkouts.remove(workoutItem)
        _workoutListMyTrains.value = oldPWorkouts
    }
}
