package com.example.vottakvot.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vottakvot.database.ExerciseDataItem
import com.example.vottakvot.domain.StatisticItem
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.database.WorkoutDataBase
import com.example.vottakvot.database.WorkoutRepository
import com.example.vottakvot.domain.Exercise
import kotlinx.coroutines.launch

// общая вью модель для всех списков тренировок
/*class TrainListViewModel(
    private val source: List<WorkoutDataItem> = listOf()
)
    : ViewModel() {

*/

class TrainListViewModel(
    application: Application,
    //dbName: String
    //private val repository: usersRepository
    //: List<UserDataItem> = listOf()
) : AndroidViewModel(application) {
    var dbName = "workout.db"

    //----------------------------------------------------------ОБЩАЯ МОДЕЛЬ СПИСКА ТРЕНИРОВОК
    // список тренировок
    val  _workoutListGeneral : LiveData<List<WorkoutDataItem>>
    val workoutListGeneral: LiveData<List<WorkoutDataItem>>

    private val  _exerciseListGeneral : LiveData<List<ExerciseDataItem>>
    val  exerciseListGeneral : LiveData<List<ExerciseDataItem>>

    // список упражнений
   // private val  _workoutListGeneral : LiveData<List<WorkoutDataItem>>
   // val workoutListGeneral: LiveData<List<WorkoutDataItem>>

    // выбранная тренировка
    var currentWorkoutId = 0
    // выбранное упражнение
    var currentExerciseId = 0



    private val repository: WorkoutRepository
    init {
        val workoutDao = WorkoutDataBase.getDatabase(application).workoutDao()
            //WorkoutDataBase.getDatabase(application, "train").workoutDao()
        repository = WorkoutRepository(workoutDao = workoutDao)
        _workoutListGeneral = repository.getAllItems()// as MutableLiveData<List<UserDataItem>>
        _exerciseListGeneral = repository.getAllExercises()
        exerciseListGeneral = _exerciseListGeneral



        workoutListGeneral = _workoutListGeneral as LiveData<List<WorkoutDataItem>>
    }

    fun makeExerciseList()
    {
        // список всех упражнений
        //val modifiedList = _exerciseListGeneral.value?.toMutableList() ?: mutableListOf()
        if (_workoutListGeneral.value!=null) {
            // для каждой тренировки из списка
            for (workout in _workoutListGeneral.value!!) {
                workout.exerciseList = getExerciseListForOneWorkout(workout.id)
            }
        }
    }


    fun getExerciseListForOneWorkout(workoutId: Int) : MutableList<ExerciseDataItem>
    {
        var items = exerciseListGeneral.value?.toMutableList() ?: mutableListOf()
        val _exerciseList = items.filter { it -> it.workoutId == workoutId} as MutableList<ExerciseDataItem>
        return _exerciseList
    }



    // поиск упражнений для каждой тренировки
    fun getAllExerciseForWorkout(workoutId: Int): LiveData<List<ExerciseDataItem>>
    {
        return  repository.getExerciseListByWorkoutId(workoutId)
    }

    fun insertWorkoutList(workoutList: List<WorkoutDataItem>)
    {
        viewModelScope.launch {
            for (workout in workoutList) {
                repository.insertWorkout(workout)
            }
        }
    }


    fun insertWorkoutWithExercise(workoutList: List<WorkoutDataItem>)
    {
        viewModelScope.launch {
            for (workout in workoutList) {
                repository.insertWorkoutWithExercise(workout)
            }

        }
    }


    fun insertListWorkoutWithExercise(workout: WorkoutDataItem)
    {
        viewModelScope.launch {
            repository.insertWorkout(workout)
        }
    }

    fun updateExerciset(exercise: ExerciseDataItem?)
    {
        if (exercise != null) {
            try {
                // currentUser = null
                viewModelScope.launch {
                    repository.updateExercise(exercise)
                }
                //setCurrentUser(null)
            } catch (e: Exception) {

            }
        }
    }

    fun updateWorkout(workout: WorkoutDataItem?)
    {
        if (workout != null) {
            try {
                // currentUser = null
                viewModelScope.launch {
                    repository.updateWorkout(workout)
                }
                //setCurrentUser(null)
            } catch (e: Exception) {

            }
        }
    }



    fun updateWorkoutWithExercise(workout: WorkoutDataItem) {
        if (workout != null) {
            try {
                viewModelScope.launch {
                    // удаление всех упражнений в тренировке
                    for (exercise in workout.exerciseList) {

                        repository.updateExercise(exercise)
                    }
                    // удаление тренировки
                    updateWorkout(workout)
                }
                //setCurrentUser(null)
            } catch (e: Exception) {

            }
        }
    }




       // fun removeExercise(id: Int) {

       // }




    fun removeExercise(id: Int)
    {
        viewModelScope.launch {
            repository.deleteExercise(id)
        }
    }

    fun removeWorkout(id: Int)
    {
        viewModelScope.launch {
            repository.deleteWorkout(id)
        }
    }

    fun removeWorkoutWithExercise(workout: WorkoutDataItem)
        {
            viewModelScope.launch {
                // удаление всех упражнений в тренировке
                for (exercise in workout.exerciseList)
                {
                    removeExercise(exercise.id)
                }
                // удаление тренировки
                removeWorkout(workout.id)
            }
        }

    fun removeOldWorkouts()
        {
            viewModelScope.launch {
                for (workout in _workoutListGeneral.value!!) {
                    // если тренировки нет в списке моих тренировок и избранных
                    if ((!workout.isAddedToMyTrainList) && (!workout.isAddedToFavourite))
                    {
                        // e
                        removeWorkoutWithExercise(workout)
                    }
                }

            }
        }

    /*
    fun removeWorkoutWithExercise(id: Int)
    {
        viewModelScope.launch {
            repository.deleteWorkoutWithExercise(id)
        }
    }
    */

    /*
    fun setNewSource(new_source: List<WorkoutDataItem>) {
        try {
            insertWorkoutWithExercise(new_source)
        }
        catch (e : Exception)
        {
            e.message?.let { Log.d("Не удалось добавить новый источник тренировок", it) }
        }
    )
     */


    fun findWorkoutById(id: Int): WorkoutDataItem {
        val modifiedList = _workoutListGeneral .value?.toMutableList() ?: mutableListOf()
        var item =  modifiedList.find{it.id == id}
        return item!!
    }

    fun findExerciseById(idWorkout: Int, idExercise: Int): ExerciseDataItem {
        val workout = findWorkoutById(idWorkout)
        var item = workout.exerciseList.find{it.id == idExercise}
        return item!!
    }

    public fun setItemByIndex(index: Int, workoutDataItem: WorkoutDataItem)
    {
        return
    }

    // получить

    public fun makeWorkoutList(source: List<WorkoutDataItem>)
    {
       // _workoutListGeneral.postValue(source)
    }
    public fun changeLikedStatusList(model: WorkoutDataItem)
    {
        changeLikedStatus(model)
    }
    public fun changeAddedStatusList(model: WorkoutDataItem)
    {
        changeAddedStatus(model)
    }

    public fun changePlayingStatusList(model: WorkoutDataItem)
    {
        changePlayingStatus(model)
    }



    // Общие функции
    fun changeLikedStatus(currentWorkout: WorkoutDataItem) {
        //val modifiedList = workoutListGeneral.value?.toMutableList() ?: mutableListOf()
        currentWorkout.isAddedToFavourite = !currentWorkout.isAddedToFavourite
        updateWorkout(currentWorkout)
    }

    fun changeAddedStatus(currentWorkout: WorkoutDataItem) {
        currentWorkout.isAddedToMyTrainList = !currentWorkout.isAddedToMyTrainList
        updateWorkout(currentWorkout)
    }
    fun changePlayingStatus(currentWorkout: WorkoutDataItem) {
        currentWorkout.isPlaying = !currentWorkout.isPlaying
        updateWorkout(currentWorkout)
    }

}
