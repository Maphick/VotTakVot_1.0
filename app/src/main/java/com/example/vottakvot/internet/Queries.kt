package com.example.vottakvot.internet

import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.data.Repository
import com.example.vottakvot.data.TransormWorkoutEntityToWorkoutDataItem
import com.example.vottakvot.domain.BodyType

fun getYourTrains(
    inquirerViewModel: InquirerViewModel,
    limit: Int = 100,
    trainListForYou: TrainListViewModel
)
{
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
    repo.makeBodyTypeRequest(bodyType, limit)
    val workoutEntities =  repo.worcoutListEntity
    val transformation = TransormWorkoutEntityToWorkoutDataItem(workoutEntities)
    val sourceListFromServer = transformation.getSourceListTrainsForYouFromServer()
    // список тренировок для Вас
    trainListForYou.setNewSource(new_source = sourceListFromServer)
    //trainListForYou = TrainListViewModel(source = sourceListFromServer)
}
