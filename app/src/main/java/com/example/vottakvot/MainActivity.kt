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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.SplashViewModel
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.navigation.SetupNavGraph
import com.example.vottakvot.screen.MainScreen
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        // заставочка
        var splashViewModel: SplashViewModel = SplashViewModel(DataStoreRepository(context = this) )
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }
        // для экранов приветствия
        var welcomeViewModel = WelcomeViewModel(DataStoreRepository(this))
        // для экранов загрузки
        var inquirerViewModel = InquirerViewModel(DataStoreRepository(this))
        /*
        // для экранов приветствия
        var welcomeViewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]
        // для экранов загрузки
        var inquirerViewModel = ViewModelProvider(this)[InquirerViewModel::class.java]
         */

        setContent {
            VotTakVotTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
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

/*
        super.onCreate(savedInstanceState)
        // заставочка
        var splashViewModel: SplashViewModel = SplashViewModel(DataStoreRepository(context = this) )
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }
        // для экранов приветствия
        var welcomeViewModel = WelcomeViewModel(DataStoreRepository(this))
        // для экранов загрузки
        var inquirerViewModel = InquirerViewModel(DataStoreRepository(this))
        setContent {
            VotTakVotTheme {
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()
                //val context =
                SetupNavGraph(
                    this,
                    navController = navController,
                    startDestination = screen,
                    welcomeViewModel = welcomeViewModel,
                    inquirerViewModel = inquirerViewModel
                )
            }
        }
    }

}



/*
super.onCreate(savedInstanceState)
//var mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
// заставочка
var splashViewModel: SplashViewModel = SplashViewModel(DataStoreRepository(context = this) )
installSplashScreen().setKeepOnScreenCondition {
    !splashViewModel.isLoading.value
}
// для экранов приветствия
var welcomeViewModel = WelcomeViewModel(DataStoreRepository(this))
// для экранов загрузки
var inquirerViewModel = InquirerViewModel(DataStoreRepository(this))
/*
// для экранов приветствия
var welcomeViewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]
// для экранов загрузки
var inquirerViewModel = ViewModelProvider(this)[InquirerViewModel::class.java]
 */

setContent {
VotTakVotTheme {
    // A surface container using the 'background' color from the theme
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
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
*/


    /*
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // заставочка
        var splashViewModel: SplashViewModel = SplashViewModel(DataStoreRepository(context = this) )
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }
        // для экранов приветствия
        var welcomeViewModel = WelcomeViewModel(DataStoreRepository(this))
        // для экранов загрузки
        var inquirerViewModel = InquirerViewModel(DataStoreRepository(this))
        setContent {
            VotTakVotTheme {
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()
                //val context =
                SetupNavGraph(
                    this,
                    navController = navController,
                    startDestination = screen,
                    welcomeViewModel = welcomeViewModel,
                    inquirerViewModel = inquirerViewModel
                )
            }
        }
    }

}

*/


 */
