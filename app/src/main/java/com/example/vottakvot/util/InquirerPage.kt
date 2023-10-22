package com.example.vottakvot.util

import androidx.annotation.DrawableRes
import com.example.vottakvot.R

sealed class InquirerPage(
    @DrawableRes
    val image: Int,
    var title: String,
    val info: String,
    val description: String,
    val question: String,
    val answers: List<String>,
    ) {
        object First : InquirerPage(
            image = R.drawable.on_boarding_pic,
            title = "ВотТакВот",
            info = "Добро пожаловать",
            description = "Мы очень рады, что Вы присоединились к нашему сообществу. Наше приложение" +
                    "создано, чтобы поддержать Ваш спортивный путь",
            question = "Какая интенсивность упражнений Вам подходит?",
            answers = listOf("Низкая", "Умеренная", "Высокая")
        )

        object Second : InquirerPage(
            image = R.drawable.on_boarding_pic,
            title = "ВотТакВот",
            info = "Спортивное приложение",
            description = "Для тех, кто хочет устроить короткую разминку длительностью 10 минут.",
            question = "Какая интенсивность упражнений Вам подходит?",
            answers = listOf("Низкая", "Умеренная", "Высокая")
        )

        object Third : InquirerPage(
            image = R.drawable.on_boarding_pic,
            title = "ВотТакВот",
            info = "Персональные тренировки",
            description = "У нас Вы сможете расширить свои тренировки и подобрать их под себя. Вы " +
                    "готовы сделать первый шаг к своей цели?",
            question = "Какая интенсивность упражнений Вам подходит?",
            answers = listOf("Низкая", "Умеренная", "Высокая")
        )

    object Fourth : InquirerPage(
        image = R.drawable.on_boarding_pic,
        title = "ВотТакВот",
        info = "Персональные тренировки",
        description = "У нас Вы сможете расширить свои тренировки и подобрать их под себя. Вы " +
                "готовы сделать первый шаг к своей цели?",
        question = "Какая интенсивность упражнений Вам подходит?",
        answers = listOf("Низкая", "Умеренная", "Высокая")
    )

}
