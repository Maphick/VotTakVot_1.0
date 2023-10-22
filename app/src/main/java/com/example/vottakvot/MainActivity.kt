package com.example.vottakvot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.SplashViewModel
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.screen.MainScreen
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // заставочка

        var splashViewModel: SplashViewModel = SplashViewModel(DataStoreRepository(context = this) )
    //    installSplashScreen().setKeepOnScreenCondition {
         //   !splashViewModel.isLoading.value
        //}

        // для экранов приветствия
        var welcomeViewModel = WelcomeViewModel(DataStoreRepository(this))
        // var welcomeViewModel = WelcomeViewModel(DataStoreRepository(this))
        // для экранов загрузки
        var inquirerViewModel = InquirerViewModel(DataStoreRepository(this))

        setContent {
            VotTakVotTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorScheme.background)
                        .padding(0.dp),
                ) {
                    val context = applicationContext
                    MainScreen(
                        context = context,
                        splashViewModel = splashViewModel,
                        welcomeViewModel = welcomeViewModel,
                        inquirerViewModel = inquirerViewModel
                    )
                }
            }
        }
    }
}
