package fr.uha.hassenforder.team.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.Workout
@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [Workout::class,Exercise::class],
    version = 1,
    exportSchema = false,
)
abstract class WorkoutDatabase : RoomDatabase(){
    abstract fun workoutDAO():WorkoutDAO
    abstract fun exerciseDAO():ExerciseDAO
    companion object{
        @Volatile
        var INSTANCE:WorkoutDatabase? = null
        fun getDatabase(context:Context):WorkoutDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    WorkoutDatabase::class.java,
                    "workout.db"
                ).build()
                INSTANCE = instance
                return  instance;
            }
        }
    }
}