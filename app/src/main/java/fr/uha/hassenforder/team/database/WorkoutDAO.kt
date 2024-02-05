package fr.uha.hassenforder.team.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.uha.hassenforder.team.model.Workout
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface WorkoutDAO{
    @Query("SELECT * FROM workouts ORDER BY date DESC LIMIT 1")
    suspend fun getLatestWorkout(): Workout

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workout: Workout)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(workouts: List<Workout>)
    @Query("SELECT COUNT(*) FROM workouts")
    fun count(): Flow<Int>
    @Query("SELECT * FROM workouts ORDER BY date DESC")
    fun getAll(): Flow<List<Workout>>
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(workout: Workout)
    @Delete
    suspend fun delete(workout: Workout)

    @Query("DELETE FROM workouts")
    suspend fun deleteAll()
    @Query("SELECT * FROM workouts WHERE workoutId =:id")
    fun getOne(id:Long):Flow<Workout>
    @Query("SELECT * FROM workouts WHERE date >= :oneWeekAgo AND date < :today ORDER BY date DESC")
    fun getWorkoutsFromLastWeek(oneWeekAgo: Date, today: Date): Flow<List<Workout>>
    @Query("SELECT * FROM workouts WHERE date >= :startOfDay AND date < :endOfDay ORDER BY date DESC LIMIT 1")
    fun getFirstWorkoutForToday(startOfDay: Date, endOfDay: Date): Flow<Workout?>

    @Query("SELECT * FROM workouts WHERE date >= :startDate AND date < :endDate ORDER BY date DESC")
    fun getWorkoutsUntilYesterday(startDate: Date, endDate: Date): Flow<List<Workout>>

}