package fr.uha.hassenforder.team.repository

import fr.uha.hassenforder.team.database.WorkoutDAO
import fr.uha.hassenforder.team.model.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date

class WorkoutRepository (
    private val workoutDAO:WorkoutDAO
)
{
    val workouts = workoutDAO.getAll();

    fun getAll(): Flow<List<Workout>> {
        return workoutDAO.getAll()
    }
    fun getWorkoutById(id: Long): Flow<Workout> {
        return workoutDAO.getOne(id)
    }

    suspend fun insertWorkouts(workouts: List<Workout>) {
        withContext(Dispatchers.IO) {
            workoutDAO.insertAll(workouts)
        }
    }
    suspend fun insertWorkout(workout: Workout){
        workoutDAO.insert(workout)
    }

    suspend fun deleteWorkout(workout: Workout){
        workoutDAO.delete(workout);
    }

    suspend fun updateWorkout(workout: Workout){
        workoutDAO.update(workout);
    }

    suspend fun getLatestWorkout(): Workout {
        return workoutDAO.getLatestWorkout()
    }

    fun getLastWeekWorkout(oneWeekAgo: Date):Flow<List<Workout>>{
        return workoutDAO.getWorkoutsFromLastWeek(oneWeekAgo, Date());
    }

    fun getHistory(): Flow<List<Workout>> {
        val calendar = Calendar.getInstance()
        calendar.set(1990, Calendar.JANUARY, 1)
        val startDate = calendar.time

        calendar.time = Date() // Aujourd'hui
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val endDate = calendar.time
        return workoutDAO.getWorkoutsUntilYesterday(startDate, endDate)
    }

    fun getFirstWorkoutForToday() : Flow<Workout?>{
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val startOfDay = calendar.time
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val endOfDay = calendar.time
        val firstWorkoutForToday = workoutDAO.getFirstWorkoutForToday(startOfDay, endOfDay)
        return firstWorkoutForToday;
    }
}