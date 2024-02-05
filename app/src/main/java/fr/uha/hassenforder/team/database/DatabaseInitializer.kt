package fr.uha.hassenforder.team.database

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import fr.uha.hassenforder.team.model.ExerciseWorkoutAssociation
import fr.uha.hassenforder.team.model.Workout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.Calendar

fun createWorkoutsList(): List<Workout> {
    val workouts = ArrayList<Workout>()
    val currentDate = Calendar.getInstance()

    for (i in 0 until 20) {
        val workoutDate = Calendar.getInstance()
        workoutDate.add(Calendar.DAY_OF_YEAR, -i)

        val workout = Workout(
            workoutName = "Workout $i",
            date = workoutDate.time
        )
        workouts.add(workout)
    }

    return workouts
}
fun createExerciseWorkoutAssociationsList(): List<ExerciseWorkoutAssociation> {
    val associations = mutableListOf<ExerciseWorkoutAssociation>()
    for (i in 1..10) {
        associations.add(ExerciseWorkoutAssociation(eid = i.toLong(), wid = i.toLong()))
    }
    return associations
}


val exercises = listOf(
    Exercise(exerciseName = "Running", exerciseType = ExerciseType.CARDIO, exerciseDuration = 30),
    Exercise(exerciseName = "Push-ups", exerciseType = ExerciseType.STRENGTH, exerciseDuration = 15),
    Exercise(exerciseName = "Yoga", exerciseType = ExerciseType.FLEXIBILITY, exerciseDuration = 45),
    Exercise(exerciseName = "HIIT", exerciseType = ExerciseType.HIGH_INTENSITY_INTERVAL_TRAINING, exerciseDuration = 20),
    Exercise(exerciseName = "Plank", exerciseType = ExerciseType.CORE, exerciseDuration = 5),
    Exercise(exerciseName = "Cycling", exerciseType = ExerciseType.CARDIO, exerciseDuration = 45),
    Exercise(exerciseName = "Squats", exerciseType = ExerciseType.STRENGTH, exerciseDuration = 20),
    Exercise(exerciseName = "Pilates", exerciseType = ExerciseType.FLEXIBILITY, exerciseDuration = 40),
    Exercise(exerciseName = "Jumping Jacks", exerciseType = ExerciseType.HIGH_INTENSITY_INTERVAL_TRAINING, exerciseDuration = 15),
    Exercise(exerciseName = "Russian Twists", exerciseType = ExerciseType.CORE, exerciseDuration = 10),
    Exercise(exerciseName = "Swimming", exerciseType = ExerciseType.CARDIO, exerciseDuration = 60),
    Exercise(exerciseName = "Pull-ups", exerciseType = ExerciseType.STRENGTH, exerciseDuration = 15),
    Exercise(exerciseName = "Stretching", exerciseType = ExerciseType.FLEXIBILITY, exerciseDuration = 30),
    Exercise(exerciseName = "Burpees", exerciseType = ExerciseType.HIGH_INTENSITY_INTERVAL_TRAINING, exerciseDuration = 20),
    Exercise(exerciseName = "Leg Raises", exerciseType = ExerciseType.CORE, exerciseDuration = 10),
    Exercise(exerciseName = "Rowing", exerciseType = ExerciseType.CARDIO, exerciseDuration = 45),
    Exercise(exerciseName = "Deadlifts", exerciseType = ExerciseType.STRENGTH, exerciseDuration = 25),
    Exercise(exerciseName = "Tai Chi", exerciseType = ExerciseType.FLEXIBILITY, exerciseDuration = 50),
    Exercise(exerciseName = "Mountain Climbers", exerciseType = ExerciseType.HIGH_INTENSITY_INTERVAL_TRAINING, exerciseDuration = 15),
    Exercise(exerciseName = "Crunches", exerciseType = ExerciseType.CORE, exerciseDuration = 10)

)


object DatabaseInitializer {
    suspend fun initializeDatabase(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val workoutDatabase = WorkoutDatabase.getDatabase(context)
                val job1 = CoroutineScope(Dispatchers.IO).launch { initializeExerciseTable(workoutDatabase) }
                val job2 = CoroutineScope(Dispatchers.IO).launch { initializeWorkoutTable(workoutDatabase) }
                val job3 = CoroutineScope(Dispatchers.IO).launch { initializeAssociationTable(workoutDatabase) }
                job1.join()
                job2.join()
                job3.join()
            } catch (e: Exception) {
                Log.e("DatabaseInitializer", "Error initializing database", e)
            }
        }
    }

    private suspend fun initializeWorkoutTable(database: WorkoutDatabase) {
        val workoutCount = database.workoutDAO().count()
        workoutCount.collect { count ->
            if (count < 5) {
                database.workoutDAO().deleteAll() // Clear all tables if needed
                val workouts = createWorkoutsList()
                database.workoutDAO().insertAll(workouts)
            }
        }
    }

    private suspend fun initializeExerciseTable(database: WorkoutDatabase) {
        val exerciseCount = database.exerciseDAO().count()
        exerciseCount.collect { count ->
            if (count < 5) {
                database.exerciseDAO().insertAll(exercises)
            }
        }
    }

    private suspend fun initializeAssociationTable(database: WorkoutDatabase) {
        val associationCount = database.exerciseWorkoutAssociationDAO().count()
        associationCount.collect { count ->
            if (count < 5) {
                val associations = createExerciseWorkoutAssociationsList()
                database.exerciseWorkoutAssociationDAO().insertAll(associations)
            }
        }
    }
}

