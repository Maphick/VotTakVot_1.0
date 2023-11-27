package com.example.vottakvot.navigation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.internet.getYourTrains
import com.example.vottakvot.ui.theme.VotTakVotTheme
import sourceListTrainsForYouExample


// экран подбора тренировок
@Composable
fun LoaderScreen(
    inquirerViewModel: InquirerViewModel,
    trainListForYou: TrainListViewModel
) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            // получение списка тренировок с сервера
            /*getYourTrains(
                inquirerViewModel = inquirerViewModel,
                limit = 100,
                trainListForYou =  trainListForYou
            )*/

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = stringResource(R.string.train_selecting),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            var isLoading by remember { mutableStateOf(true) }
            var buttonSize by remember { mutableStateOf(DpSize.Zero) }
            val density = LocalDensity.current

            OutlinedButton(
                modifier = Modifier
                    .size(100.dp)
                    .then(
                        if (buttonSize != DpSize.Zero) Modifier.size(buttonSize) else Modifier
                    )
                    .onSizeChanged { newSize ->
                        if (buttonSize == DpSize.Zero) {
                            buttonSize = with(density) {
                                newSize
                                    .toSize()
                                    .toDpSize()
                            }
                        }
                    },
                border = BorderStroke(0.dp, Color.Transparent),
                onClick = { isLoading = isLoading.not() },
            )
                {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                        )
                    } else {
                        Text(text = "Click me")
                    }
                }
        }
    }


    @Preview
    @Composable
    fun DownloadScreenWhitePrev() {
        VotTakVotTheme(
            darkTheme = false
        )
        {
            val context = LocalContext.current
            var inquirerViewModel = InquirerViewModel(DataStoreRepository(context))
            val trainListForYou: TrainListViewModel =
                TrainListViewModel(source = sourceListTrainsForYouExample)
            LoaderScreen(
                inquirerViewModel = inquirerViewModel,
                trainListForYou = trainListForYou
            )
        }
    }

    @Preview
    @Composable
    fun DownloadScreenBlackPrev() {
        VotTakVotTheme(
            darkTheme = true)
        {
            val context =  LocalContext.current
            var inquirerViewModel = InquirerViewModel(DataStoreRepository(context))
            val trainListForYou: TrainListViewModel =
                TrainListViewModel(source = sourceListTrainsForYouExample)
            LoaderScreen(
                inquirerViewModel = inquirerViewModel,
                trainListForYou = trainListForYou
            )
        }
    }
