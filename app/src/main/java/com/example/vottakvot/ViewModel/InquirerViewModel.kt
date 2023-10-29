package com.example.vottakvot.ViewModel

import androidx.lifecycle.ViewModel
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.domain.InquirerPage


class InquirerViewModel (
    private val repository: DataStoreRepository
) : ViewModel() {

    // сохранение в базу флага, был ли пройден онбординг
    fun saveInquirerState(completed: Boolean) {
        // запуск корутины в потоке ввода-вывода, чтобы распараллелить работу с базой
        // viewModelScope.launch(Dispatchers.IO) {
        //     repository.saveOnBoardingState(completed)
    }


    private var _inquirerPagesList: MutableList<InquirerPage> = mutableListOf()

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
        val inquirerPage_0 = InquirerPage(
            title = "Типы тренировок",
            question = "Какими типами тренировок Вы бы хотели заниматься чаще?",
            answers = listOf(
                "Утренняя зарядка",
                "Фитнес дома",
                "Разминка на работе",
                "Расслабление перед сном"
            ),
            isChecked =  listOf(
                false,
                true,
                true,
                true
            ),
            )
        val inquirerPage_1 = InquirerPage(
            title = "Части тела",
            question = "Какие части тела Вы бы хотели чаще прорабатывать при тренировках?",
            answers = listOf(
                "Верхняя часть тела",
                "Нижняя часть тела",
                "Пресс",
                "Всё тело"
            ),
            isChecked =  listOf(
                false,
                true,
                true,
                true
            ),
        )
        _newinquIrerPagesList.add(inquirerPage_0)
        _newinquIrerPagesList.add(inquirerPage_1)
        return _newinquIrerPagesList
    }
}


