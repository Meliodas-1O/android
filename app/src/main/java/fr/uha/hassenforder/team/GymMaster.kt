package fr.uha.hassenforder.team

import android.annotation.SuppressLint
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import fr.uha.hassenforder.team.model.Workout
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.navigation.createBottomNavigationItems
import fr.uha.hassenforder.team.ui.components.CreateBottomNavigationBar
import fr.uha.hassenforder.team.ui.screens.ExerciseDetailScreen
import fr.uha.hassenforder.team.ui.screens.ExerciseListScreen
import fr.uha.hassenforder.team.ui.screens.ExerciseScreen
import fr.uha.hassenforder.team.ui.screens.HistoryScreen
import fr.uha.hassenforder.team.ui.screens.WorkoutListScreen
import fr.uha.hassenforder.team.ui.screens.WorkoutScreen
import fr.uha.hassenforder.team.ui.theme.GymMasterTheme
import java.util.Date

@SuppressLint("RememberReturnType")
@Composable
fun GymMaster() {
    val navController = rememberNavController()
    GymMasterTheme {
        val items = createBottomNavigationItems()
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        val sessions = listOf(
            Workout(
                workoutId =1,
                workoutName = "Morning Workout",
                caloriesBurned = 200,
                date = Date(14),
                workoutDuration = "13 mn"
            ),
            // Add more sessions
            Workout(
                workoutId =3,
                workoutDuration = "13 mn",
                workoutName = "Afternoon Run",
                caloriesBurned = 250,
                date = Date(14),
            )
        )

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
                        composable(Routes.EXERCISE.name) { ExerciseListScreen(navController) }
                        composable(Routes.WORKOUT.name) { WorkoutListScreen(navController) }
                        composable(Routes.HISTORY.name) { HistoryScreen(workoutList = sessions, navController=navController)}
                        composable(Routes.EXERCISE_CREATION.name) { ExerciseScreen(navController) }
                        composable(Routes.LIST_EXERCISE.name+"/{workout_id}",
                            arguments = listOf( navArgument("workout_id"){
                            type = NavType.LongType
                            })
                        ) {
                            WorkoutScreen(navController = navController, it.arguments?.getLong("workout_id")!!.toLong())
                        }
                        composable(Routes.NEXT_SESSIONS.name) { HistoryScreen(workoutList = sessions, navController=navController)}
                        composable(
                            Routes.EXERCISE_DETAIL.name+"/{exercise_id}",
                            arguments = listOf( navArgument("workout_id"){
                                type = NavType.StringType
                            })
                            ) { ExerciseDetailScreen(exercise =Exercise(1,"running_image", ExerciseType.CARDIO, "30 minutes", ), navController=navController) }
                    }
                }
            }
        }
    }
}