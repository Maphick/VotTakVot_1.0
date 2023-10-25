package com.example.vottakvot.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vottakvot.ViewModel.GeneralViewModel
import com.example.vottakvot.ViewModel.WorkoutViewModel
import com.example.vottakvot.domain.WorkoutDataItem

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
        Row(
            modifier = Modifier.padding(
                top = 8.dp,
               //start = 8.dp
            ),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        )
        {
            Column(
                modifier = Modifier.fillMaxSize(0.7f)
            )
            {
                Row(
                    //modifier = Modifier.fillMaxWidth(0.7f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                )
                {
                    Text(
                        text = "${workoutItem.title}",
                        fontSize = 25.sp,
                        color = colorScheme.primary,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row()
                {
                        InfoIconWithText(
                            iconResId = Icons.Outlined.AccessTime,
                            text = workoutItem.time.toString() + "  мин."
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                        InfoIconWithText(
                            iconResId = Icons.Outlined.Edit,
                            text = workoutItem.body_part
                        )
                }
            }
            Spacer(modifier = Modifier.width(24.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            )
            {
                Row(
                    modifier = Modifier.padding(
                        //top = 16.dp,
                        end = 16.dp
                    ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                )
                {
                    // добавление тренировки в свои тренировки
                    IcoButton(
                        modifier = Modifier
                            .padding(
                                top = 8.dp
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
                            .padding(
                                top = 8.dp
                            ),
                        iconResId = Icons.Outlined.FavoriteBorder,
                        iconResIdPressed = Icons.Outlined.Favorite,
                        isChanged = workoutItem.isAddedToFavourite
                    )
                    {
                        onLikeClickListener(workoutItem)
                    }
                }
                IcoButton(
                    modifier = Modifier
                        .padding(
                            top = 16.dp
                        )
                        .fillMaxSize(0.6f),
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
            .size(15.dp),
        imageVector = iconResId,
        contentDescription = null,
        tint = colorScheme.onSurface
    )
}
        Spacer(modifier = Modifier.width(8.dp))
        Text(
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
        modifier = Modifier.clickable {
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
        val generalViewModel =  GeneralViewModel()
        val workoutItem = WorkoutDataItem()
        WorkoutCard(
            //workoutViewModel = workoutViewModel,
            workoutItem = workoutItem,
            // слушатели клика
            onAddedClickListener = {

            },
            onLikeClickListener = {
                generalViewModel.changeLikedStatusListSearchResult(it)
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
        val generalViewModel =  GeneralViewModel()
        val workoutItem = WorkoutDataItem()
        WorkoutCard(
            //workoutViewModel = workoutViewModel,
            workoutItem = workoutItem,
            // слушатели клика
            onAddedClickListener = {

            },
            onLikeClickListener = {
                generalViewModel.changeLikedStatusListSearchResult(it)
            },
            onPlayClickListener = {}
        )
    }
}

