package com.example.vottakvot.domain

import androidx.lifecycle.MutableLiveData

data class InquirerPage(
    val id: Int,
    val title: String,
    val question: String,
    val answers: List<String>,
    val _isCheckedList: MutableLiveData<List<Boolean>> = MutableLiveData<List<Boolean>>(),
    //val isChecked: List<Boolean>? = _isCheckedList.value
    // выбраны ли ответы

)
{
    fun changeChackedById(id: Int){
        val modifiedList = _isCheckedList .value?.toMutableList() ?: mutableListOf()
        var oldValue =  modifiedList[id]
        modifiedList[id] = !oldValue
        _isCheckedList .value = modifiedList
    }

   // val _isChecked = MutableLiveData<List<Boolean>>()
    //val isChecked: LiveData<List<Boolean>> = _isChecked
    fun changeFollowingStatus(index: Int) {

        val wasChecked = _isCheckedList.value?.get(index) ?: false
       //_isCheckedList.value = wasChecked
       //_isChecked.value = !wasChecked
    }
}
