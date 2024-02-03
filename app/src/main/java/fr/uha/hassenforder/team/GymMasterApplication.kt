package fr.uha.hassenforder.team

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import fr.uha.hassenforder.team.database.WorkoutDatabase

@HiltAndroidApp
class GymMasterApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        WorkoutDatabase.getDatabase(applicationContext)
    }
}
