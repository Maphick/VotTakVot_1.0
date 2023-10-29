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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.domain.BodyType
import com.example.vottakvot.domain.WorkoutDataItem
import sourceListTrainsForYouExample

@Composable
fun WorkoutCard(
    workoutItem: WorkoutDataItem,
    onPlayClickListener: (WorkoutDataItem) -> Unit,
    onAddedClickListener: (WorkoutDataItem) -> Unit,
    onLikeClickListener: (WorkoutDataItem) -> Unit,
) {
    // это нужно, т.к. компоуз не умеет работать с лайвдатой напрямую
    // val isPlaying = workoutViewModel.isPlaying.observeAsState(false)
    // val isAddedToMyTrainList = workoutViewModel.isAddedToMyTrainList.observeAsState(false)
    // val isAddedToFavourite = workoutViewModel.isAddedToFavourite.observeAsState(false)
    // карточка тренировки
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(140.dp),
        backgroundColor = colorScheme.surface,
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        ),
        border = BorderStroke(1.dp, colorScheme.onBackground)
    ) {
        Column(
            modifier = Modifier.padding(
                top = 16.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    //.fillMaxWidth(0.5f)
                // horizontalArrangement = Arrangement.Start,
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.75f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "Сбросить стресс перед сном",
                        //"${workoutItem.title}",
                        fontSize = 20.sp,
                        color = colorScheme.primary,
                    )
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Top
                )
                {
                    // добавление тренировки в свои тренировки
                    IcoButton(
                        modifier = Modifier
                            .size(35.dp)
                            .padding(
                                //top = 8.dp
                            ),
                        iconResId = Icons.Outlined.PlaylistAdd,
                        iconResIdPressed = Icons.Outlined.PlaylistAdd,
                        isChanged = workoutItem.isAddedToMyTrainList
                    )
                    {
                        onAddedClickListener(workoutItem)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    // добавление тренировки в избранное
                    IcoButton(
                        modifier = Modifier
                            .size(35.dp)
                            .padding(
                                //top = 8.dp
                            ),
                        iconResId = Icons.Outlined.FavoriteBorder,
                        iconResIdPressed = Icons.Outlined.Favorite,
                        isChanged = workoutItem.isAddedToFavourite
                    )
                    {
                        onLikeClickListener(workoutItem)
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(0.7f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                    )
                {
                    InfoIconWithText(
                        modifier = Modifier
                            .padding(
                                //top = 16.dp
                            ),
                        iconResId = Icons.Outlined.AccessTime,
                        text = workoutItem.time.toString() + "  мин."
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    InfoImageWithText(
                        modifier = Modifier
                            .padding(
                                //top = 8.dp
                            ),
                        painterResourceId = when(workoutItem.bodyType)
                        {
                            BodyType.FULL_BODY ->  R.drawable.body
                            BodyType.UPPER_BODY ->  R.drawable.upper
                            BodyType.BOTTOM_BODY ->  R.drawable.lower
                            BodyType.ABD ->  R.drawable.abs
                            else -> R.drawable.body
                        },

                        text = when(workoutItem.bodyType)
                        {
                            BodyType.FULL_BODY -> stringResource(R.string.full_body)
                            BodyType.UPPER_BODY ->  stringResource(R.string.upper_body)
                            BodyType.BOTTOM_BODY -> stringResource(R.string.botttom_body)
                            BodyType.ABD ->  stringResource(R.string.abd)
                            else -> stringResource(R.string.full_body)
                        }
                    )
                }
                Row(
                    modifier = Modifier,
                       // .size(60.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Top
                )
                {
                    IcoButton(
                        modifier = Modifier
                            .size(60.dp)
                            .padding(
                               // bottom = 8.dp,
                                //end = 8.dp
                            )
                            .fillMaxSize(1f),
                        iconResId = Icons.Sharp.PlayCircleFilled,
                        iconResIdPressed = Icons.Sharp.PlayCircleFilled,
                        isChanged = workoutItem.isPlaying
                    ) {
                        onPlayClickListener(workoutItem)
                    }
                }
            }
        }
    }
}


@Composable
fun InfoImageWithText(
    modifier: Modifier = Modifier,
    painterResourceId: Int,
    text: String,
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    )
    { Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
       // imageView.setColorFilter(ContextCompat.getColor(context, android.R.color.white),
       //     PorterDuff.Mode.MULTIPLY);
        Image(
            modifier = modifier
                .size(25.dp),
            painter = painterResource(id = painterResourceId),
            colorFilter = ColorFilter.tint(color = colorScheme.onSurface),
                //    = colorScheme.background,
            contentDescription = null
        )
    }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = modifier,
            text = text,
            color = colorScheme.onSurface
        )
    }
}

@Composable
fun InfoIconWithText(
    modifier: Modifier = Modifier,
    iconResId: ImageVector,
    text: String,

) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    )
{ Row(
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        modifier = modifier
            .size(25.dp)
           // .padding()
        ,
        imageVector = iconResId,
        contentDescription = null,
        tint = colorScheme.onSurface
    )
}
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = modifier,
            text = text,
            color = colorScheme.onSurface
        )
    }
}

@Composable
private fun IcoButton(
    modifier: Modifier = Modifier,
    iconResId: ImageVector,
    iconResIdPressed: ImageVector,
    isChanged: Boolean,
    onItemClickListener: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .clickable {
                onItemClickListener()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = modifier,
            imageVector = if (isChanged) {
                iconResIdPressed
            } else {
                iconResId
            },
            contentDescription = null,
            tint = if (isChanged) {
                colorScheme.primary
                    } else {
                colorScheme.onSurfaceVariant
                    }
        )
    }
}

@Preview
@Composable
fun WorkoutCardWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        //val generalViewModel =  GeneralViewModel()
        // источник данных для списк атренировок
        val source: List<WorkoutDataItem> = sourceListTrainsForYouExample
        // список тренировок
        val trainList = TrainListViewModel(
            source
        )


        val workoutItem = WorkoutDataItem()
        WorkoutCard(
            //workoutViewModel = workoutViewModel,
            workoutItem = workoutItem,
            // слушатели клика
            onAddedClickListener = {

            },
            onLikeClickListener = {
                trainList.changeLikedStatusList(it)
                //generalViewModel.changeLikedStatusListSearchResult(it)
            },
            onPlayClickListener = {}
        )
    }
}

@Preview
@Composable
fun WorkoutCardBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        //val generalViewModel =  GeneralViewModel()
        val source: List<WorkoutDataItem> = sourceListTrainsForYouExample
        // список тренировок
        val trainList = TrainListViewModel(
            source
        )
        val workoutItem = WorkoutDataItem()
        WorkoutCard(
            //workoutViewModel = workoutViewModel,
            workoutItem = workoutItem,
            // слушатели клика
            onAddedClickListener = {

            },
            onLikeClickListener = {
                trainList.changeLikedStatusList(it)
                //generalViewModel.changeLikedStatusListSearchResult(it)
            },
            onPlayClickListener = {}
        )
    }
}

