package fr.uha.hassenforder.team.repository

import fr.uha.hassenforder.team.database.ExerciseDAO
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(private val exerciseDAO: ExerciseDAO) {
    fun count(): Flow<Int> {
        return exerciseDAO.count()
    }

    suspend fun insertAll(exercises: List<Exercise>) {
        exerciseDAO.insertAll(exercises)
    }

    suspend fun insert(exercise: Exercise) {
        exerciseDAO.insert(exercise)
    }

    fun getAll(): Flow<List<Exercise>> {
        return exerciseDAO.getAll()
    }

    suspend fun update(exercise: Exercise) {
        exerciseDAO.update(exercise)
    }

    suspend fun delete(exercise: Exercise) {
        exerciseDAO.delete(exercise)
    }

    fun getOne(id: Long): Flow<Exercise> {
        return exerciseDAO.getOne(id)
    }

    fun getExercisesByType(type: ExerciseType): Flow<List<Exercise>> {
        return exerciseDAO.getExercisesByType(type)
    }

    fun getExercisesForWorkout(workoutId: Long): Flow<List<Exercise>> {
        return exerciseDAO.getExercisesForWorkout(workoutId)
    }

    fun getExercisesStartingWith(pattern: String): Flow<List<Exercise>> {
        return exerciseDAO.getExercisesStartingWith(pattern)
    }
}
