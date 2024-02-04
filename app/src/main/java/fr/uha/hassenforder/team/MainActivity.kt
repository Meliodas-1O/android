package fr.uha.hassenforder.team

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import fr.uha.hassenforder.team.database.DatabaseInitializer
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            Log.e(TAG, "Coroutine exception", exception)
            // Handle the exception appropriately
        }
        lifecycleScope.launch {
            try {
                DatabaseInitializer.initializeDatabase(applicationContext)
                Log.d(TAG, "Database initialized successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Error initializing database", e)
                // Handle the error appropriately (e.g., show an error message to the user)
            }
        }
        setContent {
            GymMaster()
        }
    }
}
