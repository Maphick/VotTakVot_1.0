package com.example.vottakvot.data

import com.example.vottakvot.domain.BodyType
import com.example.vottakvot.domain.ExerciseDataItem
import com.example.vottakvot.domain.WorkoutDataItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.Arrays
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


            val exercise = exercise?.let {
                exercise?.id?.let { it1 ->
                    ExerciseDataItem(
                        id = it1.toInt(),
                        title = it.name,
                        time = Random.nextInt(1, 3),
                        bodyType = bodyType,
                        type = Random.nextInt(0,3),
                        approaches = Random.nextInt(2,4),
                        repetitions = Random.nextInt(8,20),
                        url = it.gifUrl,
                        instructions = it.instructions.toMutableList(),
                    )
                }
            }

            if (exercise != null) {
                sourceListTrainsForYouFromServer.add(exercise)
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
            if(i+exCount > allExerciseCount -1)
                break
            var sumTime = 0
            // формирование списка упражнений
            for (j in (i..i+exCount-1)) {
                val ex = exercises[j]
                sumTime += ex.time
                exList.add(ex)
            }

            val workout = WorkoutDataItem(
                id = exercises[i].id,
                title = exercises[i].title,
                time = sumTime,
                bodyType = exercises[i].bodyType,
                type = exercises[i].type,
                exersises = exList
            )
            workoutListFromExerciseList.add(workout)
            i += exCount + 1
        }
        return workoutListFromExerciseList
    }




}

