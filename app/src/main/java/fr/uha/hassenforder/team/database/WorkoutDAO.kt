package fr.uha.hassenforder.team.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.uha.hassenforder.team.model.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workout: Workout)

    @Query("SELECT * FROM workouts")
    fun getAll(): Flow<List<Workout>>
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(workout: Workout)
    @Delete
    suspend fun delete(workout: Workout)

    @Query("SELECT * FROM workouts WHERE workoutId =:id")
    fun getOne(id:Long):Flow<Workout>
}