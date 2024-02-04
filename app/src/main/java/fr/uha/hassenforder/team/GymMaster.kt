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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.navigation.createBottomNavigationItems
import fr.uha.hassenforder.team.repository.WorkoutViewModel
import fr.uha.hassenforder.team.ui.components.CreateBottomNavigationBar
import fr.uha.hassenforder.team.ui.screens.ExerciseDetailScreen
import fr.uha.hassenforder.team.ui.screens.ExerciseCategoryScreen
import fr.uha.hassenforder.team.ui.screens.ExerciseScreen
import fr.uha.hassenforder.team.ui.screens.HistoryScreen
import fr.uha.hassenforder.team.ui.screens.WorkoutListScreen
import fr.uha.hassenforder.team.ui.screens.ExerciseListScreen
import fr.uha.hassenforder.team.ui.screens.CreateWorkoutScreen
import fr.uha.hassenforder.team.ui.theme.GymMasterTheme

@SuppressLint("RememberReturnType")
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
                        composable(Routes.EXERCISE.name) { ExerciseCategoryScreen(navController=navController) }
                        composable(Routes.WORKOUT.name) { WorkoutListScreen(navController = navController) }
                        composable(Routes.HISTORY.name) { HistoryScreen(navController=navController)}
                        composable(Routes.EXERCISE_CREATION.name) { ExerciseScreen(navController = navController) }
                        composable(Routes.LIST_EXERCISE.name+"/{source}/{id}?name={name}",
                            arguments = listOf(
                                navArgument("source") {
                                    type = NavType.StringType
                                },
                                navArgument("id") {
                                    type = NavType.StringType
                                },
                                navArgument("name") {
                                    type = NavType.StringType // Assuming name is a string
                                    nullable = true // Marking optional parameter as nullable
                                    defaultValue = null // Providing default value as null
                                }
                            )
                        ) { backStackEntry ->
                            // Access source and id from the back stack entry
                            val source = backStackEntry.arguments?.getString("source").toString();
                            val id = backStackEntry.arguments?.getString("id").toString()
                            val name = backStackEntry.arguments?.getString("name")
                            ExerciseListScreen(navController = navController, source = source, id = id, name=name)
                        }
                        composable(Routes.NEXT_SESSIONS.name) { HistoryScreen(navController=navController)}
                        composable(
                            Routes.EXERCISE_DETAIL.name+"/{exercise_id}",
                            arguments = listOf( navArgument("workout_id"){
                                type = NavType.StringType
                            })
                            ) { ExerciseDetailScreen(exercise =Exercise(1,"running_image", ExerciseType.CARDIO, "30 minutes", ), navController=navController) }
                        composable(Routes.WORKOUT_CREATION.name){ CreateWorkoutScreen(navController = navController)}
                    }
                }
            }
        }
    }
}