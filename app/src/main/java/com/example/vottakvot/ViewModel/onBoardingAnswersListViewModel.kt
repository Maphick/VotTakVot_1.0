package com.example.vottakvot.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vottakvot.domain.OnBoardingAnswerDataItem


class onBoardingAnswersListViewModel(
    private val source: List<OnBoardingAnswerDataItem>
)
: ViewModel() {

    //------------------------------------------------------ОБЩАЯ МОДЕЛЬ СПИСКА ОТВЕТОВ НА ОНБОРДИНГ
    // список тренировок
    private var _answersList = MutableLiveData<List<OnBoardingAnswerDataItem>>(source)
    val answersList: LiveData<List<OnBoardingAnswerDataItem>> = _answersList

    // изменить отмеченный чекбоксом вариант
    fun changeCheckedStatus(model: OnBoardingAnswerDataItem) {
        val modifiedList = _answersList.value?.toMutableList() ?: mutableListOf()
        modifiedList.replaceAll{
            if(it == model) {
                it.copy(isOn = !it.isOn)
            }
            else
                it
        }
        _answersList.value = modifiedList
    }

}