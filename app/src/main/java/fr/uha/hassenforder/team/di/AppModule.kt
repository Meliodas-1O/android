package fr.uha.hassenforder.team.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.uha.hassenforder.team.database.WorkoutDAO
import fr.uha.hassenforder.team.database.WorkoutDatabase
import fr.uha.hassenforder.team.repository.WorkoutRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{


    @Singleton
    @Provides
    fun provideWorkoutRepository(
    workoutDAO: WorkoutDAO
    ) = WorkoutRepository(workoutDAO)

    @Provides
    fun provideWorkoutDAO(database: WorkoutDatabase): WorkoutDAO {
        return database.workoutDAO()
    }

    @Singleton
    @Provides
    fun provideWorkoutDatabase(@ApplicationContext context: Context): WorkoutDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WorkoutDatabase::class.java,
            "workout_database"
        ).build()
    }
}