package fr.uha.hassenforder.team

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.navigation.createBottomNavigationItems
import fr.uha.hassenforder.team.ui.components.CreateBottomNavigationBar
import fr.uha.hassenforder.team.ui.screens.ExercisesScreen
import fr.uha.hassenforder.team.ui.screens.HistoryScreen
import fr.uha.hassenforder.team.ui.screens.WorkoutScreen
import fr.uha.hassenforder.team.ui.theme.GymMasterTheme

@Composable
fun GymMaster() {
    val navController = rememberNavController()
    GymMasterTheme {
        val items = createBottomNavigationItems()
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(
                bottomBar = { CreateBottomNavigationBar(items, selectedItemIndex,{ newIndex ->
                    selectedItemIndex = newIndex} ,navController, ) }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController, startDestination = Routes.HOME.name) {
                        composable(Routes.HOME.name) { HomeScreen("Home Screen") }
                        composable(Routes.EXERCISE.name) { ExercisesScreen("Exercises Screen") }
                        composable(Routes.WORKOUT.name) { WorkoutScreen("Workout Screen") }
                        composable(Routes.HISTORY.name) { HistoryScreen("History Screen")}
                    }
                }
            }
        }
    }
}