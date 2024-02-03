package fr.uha.hassenforder.team.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDAO{
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
}