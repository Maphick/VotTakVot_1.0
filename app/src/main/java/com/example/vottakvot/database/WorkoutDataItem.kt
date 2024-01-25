package com.example.vottakvot.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "workout_table")
data class WorkoutDataItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "Здоровая спина",
    var time: Int = 7,
    var bodyType: String = BodyType.FULL_BODY.toString(),
    var type: Int = 0,
    var isAddedToMyTrainList: Boolean = false,
    var isAddedToFavourite: Boolean = false,
    var isPlaying: Boolean = false,
    var description: String = "Тренировка “Здоровая спина” помогает укрепить мышцы спины, снять напряжение и боль в области спины.",
    var contraindications: String = "Данная тренировка противопоказана беременным.",
    @Ignore
    var exerciseList: MutableList<ExerciseDataItem> = mutableListOf(ExerciseDataItem())
    //LiveData<List<ExerciseDataItem>> = MutableLiveData<List<ExerciseDataItem>>()
    //MutableList<ExerciseDataItem>>
    //
    //= LiveData<List<ExerciseDataItem>>()
    //ExerciseList = ExerciseList()
    //MutableList<ExerciseDataItem> = mutableListOf(ExerciseDataItem())
) {

}




private var _firstName = MutableLiveData<String>()

// LiveData should remain public, being a read-only interface with other classes like your fragment
fun getCurrentName(): LiveData<String> {
    return _firstName
}

/*
An alternative
val firstName = LiveData<String>
    get() = _firstName
*/

fun setFirstName(firstName: String){
    _firstName.value = firstName
}

/*
data class ExerciseList(
    var exersises: MutableList<ExerciseDataItem> = mutableListOf(ExerciseDataItem())
)

class ExerciseListConverters{
    @TypeConverter
    fun convertExerciseListToJSONString(exerciseList: ExerciseList): String = Gson().toJson(exerciseList)
    @TypeConverter
    fun convertJSONStringToExerciseList(jsonString: String): ExerciseList = Gson().fromJson(jsonString,ExerciseList::class.java)
}
*/

class InstructionListConverters{
    @TypeConverter
    fun convertInstructionListToJSONString(instructionList: InstructionList): String = Gson().toJson(instructionList)
    @TypeConverter
    fun convertJSONStringToInstructionList(jsonString: String): InstructionList = Gson().fromJson(jsonString,InstructionList::class.java)
}






enum class BodyType {
    FULL_BODY, UPPER_BODY,  BOTTOM_BODY, ABD
}