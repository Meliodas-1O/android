package fr.uha.hassenforder.team.repository

import fr.uha.hassenforder.team.database.WorkoutDAO
import fr.uha.hassenforder.team.model.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WorkoutRepository (
    private val workoutDAO:WorkoutDAO
)
{
    val workouts = workoutDAO.getAll();

    fun getAll(): Flow<List<Workout>> {
        return workoutDAO.getAll()
    }
    fun getOne(id: Long){
        workoutDAO.getOne(id)
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
}