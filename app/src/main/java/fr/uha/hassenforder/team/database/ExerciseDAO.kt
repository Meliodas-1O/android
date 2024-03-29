package fr.uha.hassenforder.team.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import fr.uha.hassenforder.team.model.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDAO{
    @Query("SELECT COUNT(*) FROM exercises")
    fun count(): Flow<Int>
    @Transaction
    @Query("SELECT exercises.* FROM exercises " +
            "INNER JOIN ewas ON exercises.exerciseId = ewas.eid " +
            "WHERE ewas.wid = :workoutId")
    fun getExercisesForWorkout(workoutId: Long): Flow<List<Exercise>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(exercises: List<Exercise>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: Exercise)

    @Query("SELECT * FROM exercises")
    fun getAll(): Flow<List<Exercise>>
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(exercise: Exercise)
    @Delete
    suspend fun delete(exercise: Exercise)
    @Query("SELECT * FROM exercises WHERE exerciseId =:id")
    fun getOne(id:Long): Flow<Exercise>
    @Query("SELECT * FROM exercises WHERE exerciseType = :type")
    fun getExercisesByType(type: ExerciseType): Flow<List<Exercise>>
    @Query("SELECT * FROM exercises WHERE exerciseName LIKE :pattern || '%'")
    fun getExercisesStartingWith(pattern: String): Flow<List<Exercise>>

}