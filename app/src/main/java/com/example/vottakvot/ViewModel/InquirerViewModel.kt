package com.example.vottakvot.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.domain.InquirerPage
import com.example.vottakvot.domain.WorkoutDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class InquirerViewModel (
    private val repository: DataStoreRepository
) : ViewModel() {


    private val  _keyWord = MutableLiveData<String>()
    val keyWord : LiveData<String> = _keyWord
    fun setKeyWord(kW: String) {
        _keyWord.postValue(kW)
    }

    public fun getKeyWord(): String {
        return _keyWord.value.toString()
    }
    // выбранный ти тренировки
    private val  _selectedWorkoutType = MutableLiveData<String>()

    val selectedWorkoutType: LiveData<String> = _selectedWorkoutType

    fun setSelectedWorkoutType(workoutType : String)
    {
        _selectedWorkoutType.postValue(workoutType)
    }

    // выбранная группа мышц
    private val  _selectedMuscleGroups = MutableLiveData<String>("Все")

    var selectedMuscleGroups: LiveData<String> = _selectedMuscleGroups

    // сохранение в базу флага, был ли пройден онбординг
    fun saveInquirerState(completed: Boolean) {
        // запуск корутины в потоке ввода-вывода, чтобы распараллелить работу с базой
         viewModelScope.launch(Dispatchers.IO) {
             repository.saveOnBoardingState(completed)
    }
    }


    var _inquirerPagesList = listOf<InquirerPage>()

    //val inquirerPagesList: List<InquirerPage> = listOf<InquirerPage>()//_inquirerPagesList



   // val inquirerPagesList: LiveData<List<InquirerPage>> = _inquirerPagesList

//!!!!!!!!!!! Cannot invoke setValue on a background thread from Coroutine
    fun changeAnswerCheckedValue(pageNumber: Int, answerNumber: Int, value: Boolean)
    {
        //val modifiedList = _inquirerPagesList //.value?.toMutableList() ?: mutableListOf()
        for (i in 0.._inquirerPagesList.size-1)
        {
            if(i == pageNumber) { //  нужная страница
                val modifiedCheckedList = _inquirerPagesList[i]._isCheckedList.value?.toMutableList()  ?: mutableListOf(false, false)
                for (j in 0..modifiedCheckedList.size-1)
                {
                    modifiedCheckedList[j] = false
                }
                modifiedCheckedList[answerNumber] = value // нужный вариант ответа
                _inquirerPagesList[i]._isCheckedList.postValue(modifiedCheckedList)
                //page._isCheckedList.value = modifiedCheckedList
            }
        }
    }

    fun getInquirerPagesList(): List<InquirerPage> {
        var inquirerPagesList: List<InquirerPage> = mutableListOf()
        if (_inquirerPagesList.size != 0) {
            inquirerPagesList = _inquirerPagesList
        } else {
            inquirerPagesList = this.createExampleInquirerPageList()
            _inquirerPagesList = inquirerPagesList.toMutableList()
        }
        return inquirerPagesList
    }


    fun createExampleInquirerPageList(): List<InquirerPage> {
        val _newinquIrerPagesList: MutableList<InquirerPage> = mutableListOf()
        //val _isCheckedList =
        val inquirerPage_0 = InquirerPage(
            id = 0,
            title = "Типы тренировок",
            question = "Какими типами тренировок Вы бы хотели заниматься чаще?",
            answers = listOf(
                "Утренняя зарядка",
                "Фитнес дома",
                "Разминка на работе",
                "Расслабление перед сном"
            ),
            _isCheckedList = MutableLiveData<List<Boolean>>(listOf(
                true,
                false,
                false,
                false)
            ),
        )
        val inquirerPage_1 = InquirerPage(
            id = 1,
            title = "Части тела",
            question = "Какие части тела Вы бы хотели чаще прорабатывать при тренировках?",
            answers = listOf(
                "Верхняя часть тела",
                "Нижняя часть тела",
                "Пресс",
                "Всё тело"
            ),
            _isCheckedList = MutableLiveData<List<Boolean>>(listOf(
                true,
                false,
                false,
                false)
            ),
        )
        _newinquIrerPagesList.add(inquirerPage_0)
        _newinquIrerPagesList.add(inquirerPage_1)
        return _newinquIrerPagesList
    }
}


