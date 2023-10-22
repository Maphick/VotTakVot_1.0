package com.example.vottakvot.ui.theme

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.ViewModel.WorkoutViewModel
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.screen.SplashScreen
import com.example.vottakvot.screen.WelcomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi


@Composable
fun WorkoutCard(
    workoutViewModel: WorkoutViewModel
) {
    // rememberSaveable для переворота экрана
    // liveData - объект, на который можно подписаться и реагировтаь на изменения  в этом объекте
    // JC не умеет работать с LiveData
    // Чтобы автоматически происходила рекомпозиция и Composable ф-ии реагировали на изменения в этом объекте  isFollowing
    //  этот объект должен быт типа state, а не  LiveData
    val  isFollowed: State<Boolean> = workoutViewModel.isFollowing.observeAsState(false)
        //rememberSaveable{
      //  mutableStateOf(false)
    //}
    // observeAsState - чтобы изменения в LiveData приводили к рекомпозиции (перересовке экрана)
    // если исодное значение не передать, то объект будет содержать нулабельное значение
    Card(
        modifier = Modifier.
        padding(8.dp),
        shape = RoundedCornerShape(
            topStart = 4.dp,
            topEnd = 4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
    )
    {
        Column(
            modifier = Modifier.padding(16.dp)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Image(
                    modifier = Modifier.clip(CircleShape),
                    painter = painterResource(id = R.drawable.ic_android_black_24dp),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )
                UserStatistics("Posts", "6,950")
                UserStatistics("Followers", "436M")
                UserStatistics("Following", "76")
            }

            Text(
                text = "Instagramm",
                fontSize = 32.sp,
                fontFamily = FontFamily.Cursive,
                color = MaterialTheme.colorScheme.onSecondary
                )
            Text(
                text = "#YoursToMake",
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "www.facebook.com",
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.onSecondary
            )
            FollowButton(
                isFollowed.value
            )
            {
               workoutViewModel.changeFollowingStatus()
            }
        }
    }
}


// не управляет стейтом
// и не хранит его 
@Composable
fun FollowButton(
    isFollowed: Boolean,
    clickListener: () -> Unit
)
{
    Button(
        onClick = { clickListener()  },
        // переопределение цвета
        colors =  ButtonDefaults.buttonColors(
            containerColor = if (isFollowed) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            }
            else {
                MaterialTheme.colorScheme.primaryContainer
            }
        )
    ) {

        val text = if (isFollowed) {
            "Unfollow"
        }
        else "Follow"
        Text(text)
    }
}


@Composable
fun UserStatistics(
    title: String,
    value: String
)
{
    Column(
        modifier = Modifier
            .height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        Text(
            text = value,
            fontSize = 25.sp,
            fontFamily = FontFamily.Cursive,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondary
        )

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
        WelcomeScreen(
            //conext = context,
            navController = rememberNavController(),
            welcomeViewModel = welcomeViewModel
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
        var welcomeViewModel = WelcomeViewModel(DataStoreRepository(context))
        WelcomeScreen(
            //conext = context,
            navController = rememberNavController(),
            welcomeViewModel = welcomeViewModel
        )
    }
}
