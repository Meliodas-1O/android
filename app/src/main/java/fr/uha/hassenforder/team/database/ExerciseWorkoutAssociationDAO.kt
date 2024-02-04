package fr.uha.hassenforder.team.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseWorkoutAssociation
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseWorkoutAssociationDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(association: ExerciseWorkoutAssociation)

    @Delete
    suspend fun delete(association: ExerciseWorkoutAssociation)

    @Query("SELECT * FROM ewas")
    fun getAllAssociations(): Flow<List<ExerciseWorkoutAssociation>>

    @Query("SELECT * FROM ewas WHERE eid = :exerciseId")
    fun getWorkoutsForExercise(exerciseId: Long): Flow<List<ExerciseWorkoutAssociation>>

    @Query("SELECT EXISTS(SELECT 1 FROM ewas WHERE eid = :exerciseId AND wid = :workoutId)")
    fun checkAssociationExists(exerciseId: Long, workoutId: Long): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(associations: List<ExerciseWorkoutAssociation>)

    @Query("DELETE FROM ewas")
    suspend fun deleteAllAssociations()

    @Query("SELECT COUNT(*) FROM ewas")
    fun count(): Flow<Int>
}
