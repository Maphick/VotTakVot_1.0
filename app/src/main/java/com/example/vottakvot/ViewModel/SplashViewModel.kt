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

    private var _isOnBoardingCompleted: MutableState<Boolean> = mutableStateOf(false)
    var isOnBoardingCompleted: State<Boolean> = _isOnBoardingCompleted

    private var _isWelcomeCompleted: MutableState<Boolean> = mutableStateOf(false)
    var isWelcomeCompleted: State<Boolean> = _isWelcomeCompleted

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { onBoardingCompleted ->

                repository.readWelcomeState().collect {welcomeCompleted ->

                    _isOnBoardingCompleted.value = onBoardingCompleted
                    _isWelcomeCompleted.value = welcomeCompleted

                    // если приветствие не пройдено
                   if (!welcomeCompleted) {
                       // проходим приветствие
                       _startDestination.value = Screen.Welcome.route
                           //Screen.Splash.route
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