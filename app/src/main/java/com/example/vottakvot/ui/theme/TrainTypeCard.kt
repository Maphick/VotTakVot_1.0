package com.example.vottakvot.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.PlaylistAdd
import androidx.compose.material.icons.sharp.PlayCircleFilled
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.domain.BodyType
import com.example.vottakvot.domain.WorkoutDataItem
import sourceListTrainsForYouExample


@Composable
fun TrainTypeCard(
    modifier: Modifier,
    title: String,
    painterResourceId: Int,
    onLCardClickListener: () -> Unit,
)
{    Card(
    modifier = modifier
        //.fillMaxWidth(0.5f)
        .padding(
            //bottom = 8.dp,
            //start = 8.dp,
            //end = 8.dp
        )
        .clickable {
            onLCardClickListener()
        }
        //.fillMaxWidth(0.8f)
        .width(200.dp)
        .height(150.dp),
    backgroundColor = colorScheme.surfaceVariant,
    shape = RoundedCornerShape(
        topStart = 24.dp,
        topEnd = 24.dp,
        bottomStart = 16.dp,
        bottomEnd = 16.dp
    ),
    border = BorderStroke(1.dp, colorScheme.onSurfaceVariant)
) {
    Column(
        modifier = Modifier.padding(
             //top = 16.dp,
            // bottom = 16.dp,
            //start = 16.dp,
            //end = 16.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {
        ImageCard(
            modifier = Modifier
                .fillMaxWidth(1f),
            //title = stringResource(R.string.home_fitness),
            painterResourceId = painterResourceId,
            //onLCardClickListener = onLCardClickListener
        )
        Text(
            modifier = Modifier
                .padding(
                top = 16.dp,
                // bottom = 16.dp,
                //start = 16.dp,
                //end = 16.dp
                ),
            text = title,
            //"${workoutItem.title}",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = colorScheme.onSurfaceVariant,
        )
    }
}
}

@Composable
fun ImageCard(
    modifier: Modifier,
    //title: String,
    painterResourceId: Int,
    //onLCardClickListener: () -> Unit,
) {
    Card(
        modifier = modifier
            //.clickable {
            //    onLCardClickListener
            //}
            //.fillMaxWidth(0.5f)
            .padding(
                //bottom = 8.dp,
                //start = 8.dp,
                //end = 8.dp
            )
            //.fillMaxWidth(0.8f)
            .width(200.dp)
            .height(100.dp),
        backgroundColor = colorScheme.surfaceVariant,
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp,
            //bottomStart = 16.dp,
            //bottomEnd = 16.dp
        ),
        border = BorderStroke(1.dp, colorScheme.onSurfaceVariant)
    ) {

            Image(
                modifier = Modifier
                    .fillMaxSize(),
                    //.fillMaxWidth(1f)
                    //.fillMaxHeight(1f),
                painter = painterResource(id = painterResourceId),
                contentDescription = "",
            )
        }
    }


    @Preview
    @Composable
    fun ImageCardWhitePrev() {
        VotTakVotTheme(
            darkTheme = false
        )
        {
            TrainTypeCard(
                modifier = Modifier,
                title = stringResource(R.string.home_fitness),
                painterResourceId = R.drawable.home_fitness,
                onLCardClickListener = {
                }
            )
        }
    }

    @Preview
    @Composable
    fun ImageCardBlackPrev() {
        VotTakVotTheme(
            darkTheme = true
        )
        {
            ImageCard(
                modifier = Modifier
                    .fillMaxWidth(),
                //title = stringResource(R.string.before_bedtime),
                painterResourceId = R.drawable.before_bedtime,
                //onLCardClickListener = {
                //}
            )
        }
    }

