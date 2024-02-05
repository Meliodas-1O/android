package fr.uha.hassenforder.team.helpers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import fr.uha.hassenforder.team.model.Workout
import fr.uha.hassenforder.team.model.WorkoutDomain
import fr.uha.hassenforder.team.repository.ExerciseViewModel
import fr.uha.hassenforder.team.repository.WorkoutViewModel
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.time.ExperimentalTime

object Utils {

    val COEFFICIENTS = mapOf(
        ExerciseType.CARDIO to 10,
        ExerciseType.STRENGTH to 5,
        ExerciseType.FLEXIBILITY to 3,
        ExerciseType.HIGH_INTENSITY_INTERVAL_TRAINING to 12,
        ExerciseType.CORE to 8,
        ExerciseType.CROSSFIT to 15
    )

    fun calculateExerciseCalories(exercise: Exercise): Int {
        val coefficient = COEFFICIENTS[exercise.exerciseType] ?: 0
        return coefficient * exercise.exerciseDuration
    }

    fun calculateWorkoutCalories(exercises: List<Exercise>): Int {
        return exercises.sumOf { calculateExerciseCalories(it) }
    }

    fun calculateWorkoutDuration(exercises: List<Exercise>): Int {
        return exercises.sumOf { it.exerciseDuration }
    }

    @ExperimentalTime
    fun getOneWeekAgoDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        return calendar.time
    }

    private fun getStartOfDay(date: Date): Date {
        val calendar = Calendar.getInstance().apply {
            time = date
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return calendar.time
    }


    fun getPreviousWeekStartDate(currentDate: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val daysToSubtract = (currentDayOfWeek - Calendar.MONDAY + 7) % 7
        calendar.add(Calendar.DAY_OF_YEAR, -daysToSubtract - 7)
        return calendar.time
    }

    fun getPreviousDay(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        return calendar.time
    }

    fun getWorkoutsForLastWeek(workouts: List<Workout>): Map<Date, List<Workout>> {
        val workoutsMap = mutableMapOf<Date, List<Workout>>()
        val currentDate = Date()
        val previousWeekStartDate = getPreviousWeekStartDate(currentDate)
        val previousDay = getPreviousDay(previousWeekStartDate)

        val workoutsInRange = workouts.filter { it.date in previousDay..previousWeekStartDate }

        val calendar = Calendar.getInstance()
        calendar.time = previousWeekStartDate
        while (calendar.time < currentDate) {
            workoutsMap[calendar.time] = emptyList()
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        for (workout in workoutsInRange) {
            val startOfDay = getStartOfDay(workout.date)
            val list = workoutsMap[startOfDay]?.toMutableList() ?: mutableListOf()
            list.add(workout)
            workoutsMap[startOfDay] = list
        }

        val defaultWorkout = Workout(workoutId = 0, workoutName = "", date = Date())
        for (entry in workoutsMap.entries) {
            if (entry.value.isEmpty()) {
                workoutsMap[entry.key] = listOf(defaultWorkout)
            }
        }

        return workoutsMap
    }

    fun calculateCaloriesBurnedPerDay(workouts: List<WorkoutDomain>): List<Pair<String, Double>> {
        val aggregatedMap = LinkedHashMap<String, Double>().withDefault { 0.0 }

        for (workout in workouts) {
            val calendar = Calendar.getInstance().apply {
                time = workout.workout.date
            }
            val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())

            val currentCalories = aggregatedMap.getValue(dayOfWeek)
            val newCalories = currentCalories + calculateWorkoutCalories(workout.exercises) // Supposant que la fonction calculateWorkoutCalories calcule correctement les calories brûlées pour un entraînement
            aggregatedMap[dayOfWeek] = newCalories
        }

        val sortedList = aggregatedMap.toList().sortedBy {
            when (it.first) {
                "Mon" -> 1
                "Tue" -> 2
                "Wed" -> 3
                "Thu" -> 4
                "Fri" -> 5
                "Sat" -> 6
                "Sun" -> 7
                else -> 8
            }
        }

        return sortedList
    }
    @Composable
    fun createWorkoutDomainFromWorkouts(
        workouts: List<Workout>,
        exerciseVM: ExerciseViewModel = hiltViewModel(),
    ): List<WorkoutDomain> {
        val workoutDomains = mutableListOf<WorkoutDomain>()

        for (workout in workouts) {
            val exerciseStates = exerciseVM.getExercisesForWorkout(workout.workoutId).collectAsState(initial = emptyList())
            val exercises = exerciseStates.value
            val totalCalories = calculateWorkoutCalories(exercises)
            val totalDuration = calculateWorkoutDuration(exercises)
            val workoutDomain = WorkoutDomain(workout, exercises, totalCalories, totalDuration)
            workoutDomains.add(workoutDomain)
            }
        return workoutDomains
    }

}
