package com.example.vottakvot.domain

// 1 вариант ответа на вопрос на стронице онбординга
data class OnBoardingAnswerDataItem(
    val id: Int = 0,
    var question: String = "", // к какому вопросу относится
    var questionId: Int = 0,  // к какому вопросу относится
    var answer:  String = "", //  вариант ответа
    var isOn: Boolean = true // отмечен ли галочкой
) {

}