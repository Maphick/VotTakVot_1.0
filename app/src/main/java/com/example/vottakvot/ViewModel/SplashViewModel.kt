package com.example.vottakvot.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.navigation.navigationLogic.Screen
import kotlinx.coroutines.launch

// для экрана загрузки
class SplashViewModel(
    private val repository: DataStoreRepository
): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Splash.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { onBoardingCompleted ->
                repository.readWelcomeState().collect {welcomeCompleted ->
                    // если приветствие не пройдено
                   if (!welcomeCompleted) {
                       // проходим приветствие
                       _startDestination.value = Screen.Splash.route
                   }
                   // иначе если онбординг не пройден
                   else if (!onBoardingCompleted)
                   {
                       //  проходим онбординг
                       _startDestination.value = Screen.Inquirer.route

                   }
                   // иначе идем на домашний экран
                   else
                       _startDestination.value = Screen.Home.route
                }
            }
            _isLoading.value = false
        }
   }
}