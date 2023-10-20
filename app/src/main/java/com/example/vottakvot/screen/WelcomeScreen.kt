package com.example.vottakvot.screen

import android.app.Application
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.navigation.Screen
import com.example.vottakvot.navigation.WelcomePage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState


fun DataGeneration(welcomeViewModel: WelcomeViewModel)
{
    welcomeViewModel.createExamplePageList()
}
//  экраны приветствия
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    // context: Context,
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
            .fillMaxHeight()
            .fillMaxWidth()
            .background(
                colorScheme.surface
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,

    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            count = pagerCount,
            state = pagerState,
            verticalAlignment = Alignment.Top,
        ) { position ->
            PagerScreen(
                onBoardingPage = pages[position]
            )
        }
        Spacer(modifier = Modifier
            .height(20.dp)
        )
        BottomNavigation(
            modifier = Modifier
                // .height(80.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .weight(2f, false),
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
                .fillMaxWidth(),
               // .fillMaxHeight(1f),
            painter = painterResource(id = onBoardingPage.image),
            ico = painterResource(id = R.drawable.logo),
            contentDescription = "Pager Image",
            title = "ВотТакВот"
        )
        Spacer(modifier = Modifier
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                ),
            color = colorScheme.background,
            text = onBoardingPage.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,

        )
        Spacer(modifier = Modifier
            //.height(20.dp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(
                    top = 20.dp,
                    start = 40.dp,
                    end = 40.dp
                ),
            text = onBoardingPage.description,
            color = colorScheme.background,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Justify,
            lineHeight = 25.sp
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
        contentAlignment = Alignment.TopCenter,
    ) {
        Box (
            modifier = modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier
            .width(20.dp)
        )
        Image(painter =  painter,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White
                        ),
                        startY = size.height / 3,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.SrcOver)
                    }
                }
        )
        Row (
            modifier = Modifier
                .padding(top = 30.dp),
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
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 3.dp)

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
                .size(
                    width = 160.dp,
                    height = 40.dp
                ),
            pagerState = pagerState
        ) {
            //welcomeViewModel.saveOnBoardingState(completed = true)
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
            HorizontalPagerIndicator(
                modifier = modifier,
                activeColor = colorScheme.primary,
                inactiveColor = colorScheme.onBackground,
                pagerState = pagerState
            )
        ContinueButton(
            modifier = Modifier
                // .fillMaxWidth(1f),
                .size(
                    width = 160.dp,
                    height = 40.dp
                ),
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
            //modifier = Modifier.fillMaxHeight(),
            visible = pagerState.currentPage >= 0
        ) {
            OutlinedButton(
                modifier = modifier,
                // colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                border = BorderStroke(0.dp, Color.Transparent),
                // modifier = modifier,
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorScheme.onSecondaryContainer,
                    backgroundColor = Color.Transparent
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
            //modifier = Modifier.fillMaxHeight(),
            visible = pagerState.currentPage >= 0
        ) {
            OutlinedButton(
                modifier = modifier,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colorScheme.onSecondaryContainer,
                    backgroundColor = Color.Transparent
                ),
                border = BorderStroke(0.dp, Color.Transparent),
                onClick = onClick
            ) {
                var _text = text
                if (pagerState.currentPage != pagerCount - 1)
                    _text = "Продолжить..."
                Text(_text)
            }
        }
    }
}

@Composable
fun SplashScreen2() {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Splash Screen",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            androidx.compose.material3.Text(
                text = "ВотТакВот",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        androidx.compose.material3.Text(
            text = "Загружаем...",
            fontSize = 16.sp,
            color = colorScheme.onBackground
        )
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Preview(showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun WelcomecreenPrevDark() {
    var welcomeViewModel = WelcomeViewModel()
    WelcomeScreen(
        navController = rememberNavController(),
        welcomeViewModel = welcomeViewModel
    )
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Preview(

)
@Composable
fun WelcomecreenPrevLight() {
    var welcomeViewModel = WelcomeViewModel()
    WelcomeScreen(
        navController = rememberNavController(),
        welcomeViewModel = welcomeViewModel
    )
}