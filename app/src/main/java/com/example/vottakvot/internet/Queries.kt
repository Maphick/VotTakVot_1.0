package com.example.vottakvot.internet

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.data.Repository
import com.example.vottakvot.data.TransormWorkoutEntityToWorkoutDataItem
import com.example.vottakvot.domain.BodyType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("SuspiciousIndentation")
fun getYourTrains_(
    inquirerViewModel: InquirerViewModel,
    limit: Int = 100,
    trainListForYou: TrainListViewModel
) : Boolean
{
    var a = inquirerViewModel.getKeyWord()
    // часть тела по результатам опроса
    var bodyType: BodyType
    // список выбранных частей тела
    val bodyPartList: List<Boolean> = inquirerViewModel._inquirerPagesList[1]._isCheckedList.value!!
    if (bodyPartList[0]) { // верхняя часть тела
        bodyType = BodyType.UPPER_BODY
    }
    else if (bodyPartList[1]) // нижняя часть тела
    {
        bodyType = BodyType.BOTTOM_BODY
    }
    else if (bodyPartList[2]) // пресс
    {
        bodyType = BodyType.ABD
    }
    else
        bodyType = BodyType.FULL_BODY

    // запрос к внешнему API
    val repo = Repository()
    val isInternetOn = repo.makeBodyTypeRequest(bodyType, limit)

   // GlobalScope.launch {
     //   withContext(Dispatchers.IO) {

            if (isInternetOn) {
                val workoutEntities = repo.worcoutListEntity
                val transformation = TransormWorkoutEntityToWorkoutDataItem(workoutEntities)
                val sourceListFromServer = transformation.getSourceListTrainsForYouFromServer()
                // список тренировок для Вас
                trainListForYou.setNewSource(new_source = sourceListFromServer)
                //trainListForYou = TrainListViewModel(source = sourceListFromServer)
            }
       // }
    //}

    return isInternetOn

}


// получить список тренировок по ключевому слову
@SuppressLint("SuspiciousIndentation")
fun getYourTrains(
    inquirerViewModel: InquirerViewModel,
    limit: Int = 100,
    trainListSearched: TrainListViewModel,
    //keyWord: String
) : Boolean
{
    // часть тела по результатам опроса
    var bodyType: BodyType
    val keyWord: String? = inquirerViewModel.keyWord.value
    if (keyWord == null)
    {


       val checkedBodyType = inquirerViewModel.getInquirerPagesList()[1]._isCheckedList.value
        val a = checkedBodyType?.get(1)

        if ((checkedBodyType?.get(0) ?: false) == true)
        {
            bodyType = BodyType.UPPER_BODY
        }
        else if ((checkedBodyType?.get(1) ?: false) == true)
        {
            bodyType = BodyType.BOTTOM_BODY
        }
        else if ((checkedBodyType?.get(2) ?: false) == true)
        {
            bodyType = BodyType.ABD
        }
        else if ((checkedBodyType?.get(3) ?: false) == true)
        {
            bodyType = BodyType.FULL_BODY
        }
        else
            bodyType = BodyType.FULL_BODY

    }
    //var a = inquirerViewModel.keyWord
    else {
        when (keyWord) {
            "Пресс" -> bodyType = BodyType.ABD
            "Верхняя часть" -> bodyType = BodyType.UPPER_BODY
            "Нижняя часть" -> bodyType = BodyType.BOTTOM_BODY
            "Все тело" -> bodyType = BodyType.FULL_BODY
            else -> {
                bodyType = BodyType.FULL_BODY
                return getAllTrains(
                    limit = 10,
                    trainListPopular = trainListSearched
                )

            }

            //bodyType = BodyType.FULL_BODY
        }
    }

    // запрос к внешнему API
    val repo = Repository()
    val isInternetOn = repo.makeBodyTypeRequest(bodyType, limit)

    //GlobalScope.launch {
       // withContext(Dispatchers.IO) {

            if (isInternetOn) {
                val workoutEntities = repo.worcoutListEntity
                val transformation = TransormWorkoutEntityToWorkoutDataItem(workoutEntities)
                val sourceListFromServer = transformation.getSourceListTrainsForYouFromServer()
                // список тренировок для Вас
                trainListSearched.setNewSource(new_source = sourceListFromServer)
                //trainListForYou = TrainListViewModel(source = sourceListFromServer)
            }
       // }
  //  }
    return isInternetOn
}

// получить список всех тренировок
@SuppressLint("SuspiciousIndentation")
fun getAllTrains(
    limit: Int = 10,
    trainListPopular: TrainListViewModel,
) : Boolean
{
    // запрос к внешнему API
    val repo = Repository()
    val isInternetOn = repo.makeAllWorkoutsRequest(limit)

    if (isInternetOn) {
        val workoutEntities = repo.worcoutListEntity
        val transformation = TransormWorkoutEntityToWorkoutDataItem(workoutEntities)
        val sourceListFromServer = transformation.getSourceListTrainsForYouFromServer()
        // список тренировок для Вас
        trainListPopular.setNewSource(new_source = sourceListFromServer)
        //trainListForYou = TrainListViewModel(source = sourceListFromServer)
    }
    // }
    //  }
    return isInternetOn
}

