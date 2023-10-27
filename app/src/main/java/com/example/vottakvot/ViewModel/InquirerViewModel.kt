package com.example.vottakvot.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vottakvot.data.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class InquirerViewModel (
    private val repository: DataStoreRepository
) : ViewModel() {

    // сохранение в базу флага, был ли пройден онбординг
    fun saveOnBoardingState(completed: Boolean) {
        // запуск корутины в потоке ввода-вывода, чтобы распараллелить работу с базой
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveOnBoardingState(completed)
        }
    }

}