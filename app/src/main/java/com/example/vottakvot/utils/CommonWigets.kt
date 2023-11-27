package com.example.vottakvot.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.PlaylistAdd
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.navigation.screens.IconCloseButton
import com.example.vottakvot.ui.theme.VotTakVotTheme
import sourceListTrainsForYouExample


@Composable
fun TextBlock(
    modifier: Modifier = Modifier.fillMaxWidth(1f),
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    textColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onBackground,
    text: String
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(
                //start = 16.dp,
                //top = 16.dp
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color =  textColor
        )
    }
}

@Composable
fun HeaderBlock(
    text: String = "Тренировка",
    navController: NavHostController
)
{
    Box(
        modifier = Modifier
            .padding(
                top = 8.dp,
                start = 8.dp
            )
            .fillMaxWidth(1f),
        // verticalAlignment = Alignment.CenterVertically,
        // horizontalArrangement = Arrangement.Center
    )
    {
        IconCloseButton(
            modifier = Modifier
                .size(
                    35.dp
                )
                .padding(
                    top = 8.dp,
                    //start = 8.dp
                ),
            horizontalArrangement = Arrangement.Start,
            iconResId = Icons.Outlined.ArrowBackIos,
            iconResIdPressed = Icons.Outlined.ArrowBackIos,
            isChanged = true
        )
        {
            navController.popBackStack()
        }
        Text(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(
                    // start = 8.dp
                ),
            text = text,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            //fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
        )
        IconCloseButton(
            modifier = Modifier
                .size(
                    35.dp
                )
                .padding(
                    // top = 16.dp,
                    //start = 8.dp
                ),
            iconResId = Icons.Outlined.PlaylistAdd,
            iconResIdPressed = Icons.Outlined.PlaylistAdd,
            isChanged = true
        )
        {
            navController.popBackStack()
        }
    }
}

