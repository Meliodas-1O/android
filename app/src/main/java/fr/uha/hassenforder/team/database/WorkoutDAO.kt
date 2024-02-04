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
    @Query("SELECT * FROM workouts ORDER BY workoutId DESC LIMIT 1")
    suspend fun getLatestWorkout(): Workout

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workout: Workout)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(workouts: List<Workout>)
    @Query("SELECT COUNT(*) FROM workouts")
    fun count(): Flow<Int>
    @Query("SELECT * FROM workouts ORDER BY workoutId DESC")
    fun getAll(): Flow<List<Workout>>
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(workout: Workout)
    @Delete
    suspend fun delete(workout: Workout)

    @Query("DELETE FROM workouts")
    suspend fun deleteAll()
    @Query("SELECT * FROM workouts WHERE workoutId =:id")
    fun getOne(id:Long):Flow<Workout>
}