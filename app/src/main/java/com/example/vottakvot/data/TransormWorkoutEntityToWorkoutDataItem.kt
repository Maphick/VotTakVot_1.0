package com.example.vottakvot.data

import com.example.vottakvot.database.BodyType
import com.example.vottakvot.database.ExerciseDataItem
import com.example.vottakvot.database.InstructionList
import com.example.vottakvot.database.WorkoutDataItem
import kotlin.random.Random


class TransormWorkoutEntityToWorkoutDataItem(
    private val workoutEntities: List<WorcoutListEntity.ExerciseEntity>
)
{
    final val allExerciseCount = 100

    fun getExerciseListForYouFromServer(): List<ExerciseDataItem> {
        var sourceListTrainsForYouFromServer  = mutableListOf<ExerciseDataItem>()
       // test()
        for (exercise in workoutEntities) {
            val bodyType: BodyType =
                    when (exercise?.bodyPart) {
                        "upper arms" -> BodyType.UPPER_BODY
                        "lower arms" -> BodyType.UPPER_BODY
                        "shoulders" -> BodyType.UPPER_BODY
                        "chest" -> BodyType.UPPER_BODY
                        "neck" -> BodyType.UPPER_BODY

                        "upper legs" -> BodyType.BOTTOM_BODY
                        "lower legs" -> BodyType.BOTTOM_BODY

                        "waist" -> BodyType.ABD
                        "back" -> BodyType.ABD

                        "cardio" -> BodyType.FULL_BODY

                        else -> BodyType.FULL_BODY
                    }


            val _exercise =
                    ExerciseDataItem(
                        id = exercise.id.toInt(),
                        title = exercise.name,
                        time = Random.nextInt(1, 3),
                        bodyType = bodyType.toString(),
                        type = Random.nextInt(0,3),
                        approaches = Random.nextInt(2,4),
                        repetitions = Random.nextInt(8,20),
                        url = exercise.gifUrl,
                        instructionList = InstructionList(),
                       // instructions =
                        //it.instructions
                    )
            _exercise.instructionList.instructions =  exercise.instructions

            if (_exercise != null) {
                sourceListTrainsForYouFromServer.add(
                    _exercise
                )
            }

        }
        return sourceListTrainsForYouFromServer
    }


    // для формирования списка тренировок из списока упражнений
    fun getSourceListTrainsForYouFromServer(): List<WorkoutDataItem>
    {
        var workoutListFromExerciseList  = mutableListOf<WorkoutDataItem>()
        val exercises = getExerciseListForYouFromServer()
        val allExerciseCount = exercises.size
        var i = 0
        while (i < allExerciseCount)
        {
            // кол-во тренировок в упражнении
            val exCount = Random.nextInt(4,10)
            var exList = mutableListOf<ExerciseDataItem>()
                //MutableLiveData(listOf<ExerciseDataItem>())
                //mutableListOf<ExerciseDataItem>()
                //MutableLiveData<List<ExerciseDataItem>>()
                //mutableListOf<ExerciseDataItem>()
            if(i+exCount > allExerciseCount -1)
                break
            var sumTime = 0
            // формирование списка упражнений
            for (j in (i..i+exCount-1)) {
                val ex = exercises[j]
                ex.workoutId = exercises[i].id
                sumTime += ex.time
                exList.add(ex)
            }

            val workout = WorkoutDataItem(
                id = exercises[i].id,
                title = exercises[i].title,
                time = sumTime,
                bodyType = exercises[i].bodyType,
                type = exercises[i].type,
                exerciseList = mutableListOf<ExerciseDataItem>()
               // exerciseList = exList,
               // exersises = exList
            )
            workout.exerciseList = exList
            workoutListFromExerciseList.add(workout)
            i += exCount + 1
        }
        return workoutListFromExerciseList
    }




}

