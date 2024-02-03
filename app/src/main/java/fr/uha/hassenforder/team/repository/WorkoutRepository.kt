package fr.uha.hassenforder.team.repository

import fr.uha.hassenforder.team.database.WorkoutDAO
import fr.uha.hassenforder.team.model.Workout

class WorkoutRepository (
    private val workoutDAO:WorkoutDAO
){
    val workouts = workoutDAO.getAll();

    fun getAll(){
        workoutDAO.getAll()
    }
    fun getOne(id: Long){
        workoutDAO.getOne(id)
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

}