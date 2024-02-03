package fr.uha.hassenforder.team.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val workoutId: Long = 0,
    val workoutName: String,
    val workoutDuration: String,
    val caloriesBurned: Int,
    val date: Date,
)
