package fr.uha.hassenforder.team.model

sealed class ExerciseSource {
    data class FromWorkout(val workoutId: Long) : ExerciseSource()
    data class FromExerciseType(val type: ExerciseType) : ExerciseSource()
}