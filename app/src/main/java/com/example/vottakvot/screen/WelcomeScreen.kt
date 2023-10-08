package com.example.vottakvot.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.vottakvot.navigation.Screen
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.util.WelcomePage
import com.google.accompanist.pager.*

//  экраны приветствия


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    //  все страницы приветствия
    val pages = listOf(
        WelcomePage.First,
        WelcomePage.Second,
        WelcomePage.Third
    )
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {

        // переход по страницам приветствия
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerWelcomeScreen(welcomePage = pages[position])
        }

        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState
        )
        FinishWelcomeButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            // сохранить состояние онбординга
            // онбординг пройден
            welcomeViewModel.saveOnBoardingState(completed = true)
            navController.popBackStack()

            // идем на экран опросника
            navController.navigate(Screen.Inquirer.route)
        }
    }
}


// конфигурация экрана приветствия
@Composable
fun PagerWelcomeScreen(welcomePage: WelcomePage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // установка названия
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 200.dp)
                .padding(bottom = 32.dp),
            text = welcomePage.title,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        // установка картинки
        Image(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .fillMaxHeight(0.3f),
            painter = painterResource(id = welcomePage.image),
            contentDescription = "Pager Image"
        )
        // установка информации
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 32.dp),
            text = welcomePage.info,
            fontSize = MaterialTheme.typography.h4.fontSize,
             //fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        // установка описания
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = welcomePage.description,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            color =  MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}


// кнопка перехода к опросному этапу онбординга
//@ExperimentalAnimationApi
//@ExperimentalPagerApi
@Composable
fun FinishWelcomeButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
                Text(text = "Да, конечно")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FirstWelcomeScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerWelcomeScreen(welcomePage = WelcomePage.First)
    }
}

@Composable
@Preview(showBackground = true)
fun SecondWelcomeScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerWelcomeScreen(welcomePage = WelcomePage.Second)
    }
}

@Composable
@Preview(showBackground = true)
fun ThirdWelcomeScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerWelcomeScreen(welcomePage = WelcomePage.Third)
    }
}