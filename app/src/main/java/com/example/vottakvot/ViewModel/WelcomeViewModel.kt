package com.example.vottakvot.ViewModel

import androidx.lifecycle.ViewModel
import com.example.vottakvot.R
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.navigation.WelcomePage


class WelcomeViewModel(
    //dataStoreRepository: DataStoreRepository
) : ViewModel() {
    private var _welcomePagesList: MutableList<WelcomePage> = mutableListOf()


    fun getWelcomePagesList(): List<WelcomePage>
    {
        var welcomePagesList : List<WelcomePage> = mutableListOf()
        if (_welcomePagesList.size != 0) {
            welcomePagesList = _welcomePagesList
        }
        else {
            welcomePagesList = this.createExamplePageList()
            _welcomePagesList = welcomePagesList.toMutableList()
        }
        return welcomePagesList
    }

    fun createExamplePageList() : List<WelcomePage> {
        val _newWelcomePagesList: MutableList<WelcomePage> = mutableListOf()
        /*
        for(i in 0..5) {
            val welcomePage_i = WelcomePage(
                image = R.drawable.welcome_0,
                title = "Meeting",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
            )
            welcomePagesList.add(welcomePage_i)
        }
        */
        val welcomePage_0 = WelcomePage(
            image = R.drawable.welcome_0,
            title = "Добро пожаловать!",
            description = "Мы рады, что Вы присоединились к нашему спортивному сообществу. " +
                    "Наше приложение здесь, чтобы поддержать Ваш спортивный путь."
        )
        _newWelcomePagesList.add(welcomePage_0)
        val welcomePage_1 = WelcomePage(
            image = R.drawable.welcome_1,
            title = "Спортивное приложение",
            description = "Для тех, кто хочет заниматься короткими эффективными тренировками " +
                    "по 7-15 минут. Давайте воплотим Ваши спортивные мечты в жизнь!"
        )
        _newWelcomePagesList.add(welcomePage_1)
        val welcomePage_2 = WelcomePage(
            image = R.drawable.welcome_2,
            title = "Персональные тренировки",
            description = "Здесь Вы сможете подобрать для себя комплекс упражнений и сделать свои " +
                    "тренировки  регулярными. Готовы сделать первый шаг к своей цели?"
        )
        _newWelcomePagesList.add(welcomePage_2)
        return _newWelcomePagesList
    }
}

