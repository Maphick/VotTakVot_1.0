package com.example.vottakvot.domain

data class InquirerPage(
    val title: String,
    val question: String,
    val answers: List<String>,
    // выбраны ли ответы
    val isChecked: List<Boolean>
)
