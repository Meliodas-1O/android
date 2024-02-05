package fr.uha.hassenforder.team.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Long = 0,
    val exerciseName: String,
    val exerciseType: ExerciseType,
    val exerciseDuration: Int,
)