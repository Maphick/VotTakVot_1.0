package com.example.vottakvot.screen

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.R
import com.example.vottakvot.navigation.Screen
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.navigation.WelcomePage
import com.example.vottakvot.ui.theme.dark
import com.google.accompanist.pager.*
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


fun DataGeneration(welcomeViewModel: WelcomeViewModel)
{
    welcomeViewModel.createExamplePageList()
}
//  экраны приветствия
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    context: Context,
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel
) {
    // заполнение моками
    DataGeneration(welcomeViewModel)
    //  все страницы приветствия
    val pages = welcomeViewModel.getWelcomePagesList()
    if (pages.size == 0) {
        navController.navigate(Screen.Home.route)
        return
    }
    val pagerCount = pages.size
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(androidx.compose.material3.MaterialTheme.colorScheme.surface)
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
                //.weight(10f),
            count = pagerCount,
            state = pagerState,
            verticalAlignment = Alignment.Top,
        ) { position ->
            PagerScreen(
                onBoardingPage = pages[position]
            )
        }

        BottomNavigation(
            modifier = Modifier.
            fillMaxWidth(),
            navController = navController,
            pagerState = pagerState,
            pagerCount = pagerCount
        )

    }
}

@Composable
fun PagerScreen(onBoardingPage: WelcomePage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ImageAndText(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.6f),
            painter = painterResource(id = onBoardingPage.image),
            ico = painterResource(id = R.drawable.logo),
            contentDescription = "Pager Image",
            title = "ВотТакВот"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    20.dp
                ),
            color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
            text = onBoardingPage.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(
                   top = 20.dp
                ),
            text = onBoardingPage.description,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun ImageAndText(
    modifier: Modifier = Modifier,
    painter: Painter,
    ico : Painter,
    contentDescription: String,
    title: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
        )
        Row (
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(1f)
                .fillMaxHeight(0.25f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top

        )
        {
            Image(
                painter = ico,
                contentDescription = contentDescription,
                modifier = Modifier
            )

            Text(
                text = title,
                fontSize = 36.sp,
                fontFamily = FontFamily.Cursive,
                color = androidx.compose.material3.MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(top = 5.dp)

            )
        }

    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    pagerState: PagerState,
    pagerCount: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
            //.fillMaxHeight(),
        contentAlignment = BottomCenter

    )
    {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
        .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    )
    {
        SkipButton(
           modifier = Modifier
               .fillMaxWidth(1f),
            pagerState = pagerState
        ) {
            //welcomeViewModel.saveOnBoardingState(completed = true)
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
        HorizontalPagerIndicator(
            activeColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
            inactiveColor = Color(R.color.inactive),
            pagerState = pagerState
        )
        ContinueButton(
            modifier = Modifier
                .fillMaxWidth(1f),
            pagerState = pagerState,
            text = "Да, конечно!",
            pagerCount = pagerCount
        ) {
            //welcomeViewModel.saveOnBoardingState(completed = true)
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
    }
    }




}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SkipButton(
    modifier: Modifier,
    pagerState: PagerState,
    text: String = "Не сейчас",
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxHeight(),
            visible = pagerState.currentPage >= 0
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(

                    contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onSecondaryContainer,
                    backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background
                )
            ) {
                Text(text)
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun ContinueButton(
    modifier: Modifier,
    pagerState: PagerState,
    pagerCount: Int,
    text: String = "Да, конечно!",
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxHeight(),
            visible = pagerState.currentPage >= 0
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(

                    contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onSecondaryContainer,
                    backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background
                )
            ) {
                var _text = text
                if (pagerState.currentPage != pagerCount-1)
                    _text = "Продолжить..."
                Text(_text)
            }
        }
    }
}