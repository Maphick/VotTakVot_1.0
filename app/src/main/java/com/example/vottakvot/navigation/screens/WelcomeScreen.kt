package com.example.vottakvot.navigation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.domain.WelcomePage
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


fun WelcomeDataGeneration(
    welcomeViewModel: WelcomeViewModel,
    inquirerViewModel: InquirerViewModel
)
{
    welcomeViewModel.createExampleWelcomePageList()
}

//  экраны приветствия
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    //context: Context,
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel,
    inquirerViewModel: InquirerViewModel
) {
    // заполнение моками
    WelcomeDataGeneration(
        welcomeViewModel,
        inquirerViewModel
    )
    //  все страницы приветствия
    val pages = welcomeViewModel.getWelcomePagesList()
   // if (pages.size == 0) {
  /*  if (true)
    {
        navController.navigate(Screen.Home.route)
        navController.popBackStack()
        return
    }

   */
    val pagerCount = pages.size
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.surface),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
                //.weight(10f),
            count = pagerCount,
            state = pagerState,
            verticalAlignment = Alignment.Top,
        ) { position ->
            WelcomePagerScreen(
                welcomePage = pages[position]
            )
        }

        Spacer(
            modifier = Modifier
                .height(0.dp)
        )
        Column(
            modifier = Modifier
                //.background(Color.Yellow)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            WelcomeBottomNav(
                welcomeViewModel = welcomeViewModel,
                inquirerViewModel = inquirerViewModel,
                navController = navController,
                pagerState = pagerState,
                pagerCount = pagerCount
            )
        }

    }
}

@Composable
fun WelcomePagerScreen(welcomePage: WelcomePage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ImageAndText(
            modifier = Modifier,
            painter = painterResource(id = welcomePage.image),
            ico = painterResource(id = R.drawable.logo),
            contentDescription = "Pager Image",
            title = stringResource(R.string.vot_tak_vot),
            colorText = welcomePage.colorText
        )
        Spacer(modifier = Modifier
            .width(20.dp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp
                ),
            color = colorScheme.onBackground,
            text = welcomePage.title,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,

        )
        Spacer(modifier = Modifier
            .height(0.dp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(
                    top = 50.dp,
                    start = 50.dp,
                    end = 50.dp
                ),
            text = welcomePage.description,
            color = colorScheme.onBackground,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Justify,
            lineHeight = 25.sp
        )
    }
}

@Composable
fun ImageAndText(
    modifier: Modifier,
    painter: Painter,
    ico: Painter,
    contentDescription: String,
    title: String,
    colorText: Color
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.54f),
    contentAlignment = Alignment.TopCenter,
    ) {
        val gradient_color = colorScheme.surface
        Image(painter =  painter,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            gradient_color
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
        Row(
            modifier = Modifier
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {

            Image(
                painter = ico,
                contentDescription = "Splash Screen",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            androidx.compose.material3.Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = colorText
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun WelcomeBottomNav(
    welcomeViewModel: WelcomeViewModel,
    inquirerViewModel:InquirerViewModel,
    navController: NavHostController,
    pagerState: PagerState,
    pagerCount: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Row(
            modifier = Modifier
                .weight(0.4f),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        )
        {
            val skipText = stringResource(R.string.not_now)
            SkipButton(
                modifier = Modifier,
                    // .fillMaxWidth(1f),
                    /*.size(
                        width = 160.dp,
                        height = 80.dp
                    ),*/
                pagerState = pagerState,
                text = skipText,
                pagerCount = pagerCount
            ) {
                // к предыдущей странице приветствия
                if (pagerState.currentPage != pagerCount - 1)
                    GlobalScope.launch {
                        withContext(Dispatchers.Main) {
                            pagerState.scrollToPage(
                                pagerState.currentPage - 1,
                                pageOffset = 0f
                            )
                            }
                }
                else {
                    //  c последней страницы приветствия идём на главный экран БЕЗ ОНБОРДИНГА
                    //  и сохраняем флаг о том, что приветствие пройдено
                    welcomeViewModel.saveWelcomeScreenState(completed = true)
                    //  и сохраняем флаг о том, что онбординг пропущен
                    inquirerViewModel.saveInquirerState(completed = false)
                    navController.navigate(Screen.Home.route)

                    //navController.popBackStack()
                }
            }
        }
        Row(
            modifier = Modifier
                .weight(0.1f)

        )
        {
            HorizontalPagerIndicator(
                modifier = Modifier
                    .padding(
                        bottom = 20.dp
                    )
                    .width(50.dp),
                    //.fillMaxHeight(),
                activeColor = colorScheme.primary,
                inactiveColor = colorScheme.onSecondaryContainer,
                pagerState = pagerState
            )
        }
        Row(
            modifier = Modifier
                .weight(0.4f),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        )
        {
            val continueText = stringResource(R.string.continue_)
            ContinueButton(
                modifier = Modifier ,
                    //.width = 160.dp,
                    /*.size(
                        width = 160.dp,
                        height = 80.dp
                    ),
                     */
                pagerState = pagerState,
                text = continueText,
                pagerCount = pagerCount
            ) {

                //  c последней страницы приветствия идём на экран с онбордингом
                //  и сохраняем флаг о том, что приветствие пройдено
                if (pagerState.currentPage == pagerCount - 1) {
                    //  онбординг пройден
                    welcomeViewModel.saveWelcomeScreenState(completed = true)
                    navController.navigate(Screen.Inquirer.route)
                    //navController.popBackStack()
                }

                // к следующей странице приветствия
                else {
                    GlobalScope.launch {
                        withContext(Dispatchers.Main) {
                            pagerState.scrollToPage(
                                pagerState.currentPage + 1,
                                pageOffset = 0f
                            )
                        }
                    }
                }

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
    pagerCount: Int,
    text: String,
    onClick: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //AnimatedVisibility
        AnimatedVisibility(
            //modifier = modifier,
            //fillMaxHeight(),
            visible = pagerState.currentPage > 0
        ) {
            OutlinedButton(
                modifier = modifier,
                border = BorderStroke(0.dp, Color.Transparent),
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorScheme.onSecondaryContainer,
                    backgroundColor = Color.Transparent
                )
            ) {
                var _text = text
                if (pagerState.currentPage != pagerCount  - 1)
                    _text = stringResource(R.string.back)
                else
                    _text = stringResource(R.string.not_now)
                Text(
                    text = _text,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onBackground,
                )
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
    text: String,
    onClick: () -> Unit
) {

    val col = colorScheme.onSecondaryContainer
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AnimatedVisibility(
            visible = pagerState.currentPage >= 0
        ) {
            OutlinedButton(
                colors = ButtonDefaults.outlinedButtonColors
                    (
                    contentColor = colorScheme.onSecondaryContainer,
                    backgroundColor = Color.Transparent),
                border = BorderStroke(0.dp, Color.Transparent
                ),
                onClick = onClick
            ) {
                var _text = text
                if (pagerState.currentPage != pagerCount - 1)
                    _text = stringResource(R.string.continue_dot)
                else
                    _text = stringResource(R.string.yes_of_cource)
                Text(
                    text = _text,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onBackground,
                )
            }
        }
    }
}



@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Preview
@Composable
fun DarkWelcomeScreenPrev() {
    VotTakVotTheme(
        darkTheme = true
    )
    {
        val context =  LocalContext.current
        var welcomeViewModel = WelcomeViewModel(DataStoreRepository(context))
        val inquirerViewModel = InquirerViewModel(DataStoreRepository(context))
        WelcomeScreen(
            //conext = context,
            navController = rememberNavController(),
            welcomeViewModel = welcomeViewModel,
            inquirerViewModel = inquirerViewModel
        )
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Preview
@Composable
fun LightWelcomeScreenPrev() {
    VotTakVotTheme(
        darkTheme = false
    )
    {
        val context =  LocalContext.current
        val welcomeViewModel = WelcomeViewModel(DataStoreRepository(context))
        val inquirerViewModel = InquirerViewModel(DataStoreRepository(context))
        WelcomeScreen(
            //conext = context,
            navController = rememberNavController(),
            welcomeViewModel = welcomeViewModel,
            inquirerViewModel = inquirerViewModel
        )
    }
}
