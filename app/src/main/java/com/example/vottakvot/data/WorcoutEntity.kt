package com.example.vottakvot.data


class WorcoutListEntity {
    data class OneWorkoutEntity(
        val workouts: List<ExerciseEntity>
    )

    data class ExerciseEntity(
        val bodyPart: String,
        val equipment: String,
        val gifUrl: String,
        val id: String,
        val name: String,
        val target: String,
        val secondaryMuscles: List<String>,
        val instructions: List<String>
    )
    {
        override fun toString() = "$id $name $bodyPart"
    }

    data class OneSecondaryMusclesEntity(
        val muscle: String
    )
    data class OneInstructionEntity(
        val step: String
    )

}