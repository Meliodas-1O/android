package fr.uha.hassenforder.team.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val workoutId: Long = 0,
    val workoutName: String,
    val date: Date,
)

data class WorkoutDomain(
    val workout: Workout,
    val exercises: List<Exercise>,
    val caloriesBurned: Int,
    val duration: Int
)