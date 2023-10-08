package com.example.vottakvot.util

import androidx.annotation.DrawableRes
import com.example.vottakvot.R

sealed class WelcomePage(
    @DrawableRes
    val image: Int,
    val title: String,
    val info: String,
    val description: String
) {
    object First : WelcomePage(
        image = R.drawable.on_boarding_pic,
        title = "ВотТакВот",
        info = "Добро пожаловать",
        description = "Мы очень рады, что Вы присоединились к нашему сообществу. Наше приложение" +
                "создано, чтобы поддержать Ваш спортивный путь"
    )

    object Second : WelcomePage(
        image = R.drawable.on_boarding_pic,
        title = "ВотТакВот",
        info = "Спортивное приложение",
        description = "Для тех, кто хочет устроить короткую разминку длительностью 10 минут."
    )

    object Third : WelcomePage(
        image = R.drawable.on_boarding_pic,
        title = "ВотТакВот",
        info = "Персональные тренировки",
        description = "У нас Вы сможете расширить свои тренировки и подобрать их под себя. Вы " +
                "готовы сделать первый шаг к своей цели?"
    )
}
