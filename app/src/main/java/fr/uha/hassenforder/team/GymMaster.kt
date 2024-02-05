package fr.uha.hassenforder.team

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import fr.uha.hassenforder.team.helpers.Utils
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
import fr.uha.hassenforder.team.ui.screens.WorkoutDetailScreen
import fr.uha.hassenforder.team.ui.theme.GymMasterTheme
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@SuppressLint("RememberReturnType")
@Composable
fun GymMaster(
    workoutViewModel: WorkoutViewModel = hiltViewModel(),
    ) {
    val oneWeekAgo = Utils.getOneWeekAgoDate()
    val workoutsFromLastWeekState = workoutViewModel.getLastWeekWorkouts(oneWeekAgo).collectAsState(initial = emptyList())
    val workoutsFromLastWeek = workoutsFromLastWeekState.value

    val sessionsStates  = workoutViewModel.workouts.collectAsStateWithLifecycle(initialValue = emptyList())
    val workoutList = sessionsStates.value
    val workoutDomains = Utils.createWorkoutDomainFromWorkouts(workoutsFromLastWeek)
    val caloriesBurnedPerDay = Utils.calculateCaloriesBurnedPerDay(workoutDomains)

    val firstWorkoutState = workoutViewModel.getFirstWorkoutForToday().collectAsState(initial = null);
    val firstWorkout = firstWorkoutState.value


    val caloriesBurned = 0

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
                        composable(Routes.HOME.name) { HomeScreen(caloriesBurnedPerDay = caloriesBurnedPerDay, nextWorkout= firstWorkout, calories = caloriesBurned) }
                        composable(Routes.EXERCISE.name) { ExerciseCategoryScreen(navController=navController) }
                        composable(Routes.WORKOUT.name) { WorkoutListScreen(workouts = workoutList,navController = navController) }
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
                        composable(
                            Routes.EXERCISE_DETAIL.name+"/{exercise_id}",
                            arguments = listOf(
                                navArgument("exercise_id"){
                                    type = NavType.LongType
                                }
                            )
                        ){backStackEntry ->
                            val id = backStackEntry.arguments?.getLong("exercise_id")!!.toLong()
                            ExerciseDetailScreen(
                            navController = navController,
                            exerciseId =id
                        )}
                        composable(Routes.NEXT_SESSIONS.name) { HistoryScreen(navController=navController)}
                        composable(Routes.WORKOUT_CREATION.name){ CreateWorkoutScreen(navController = navController)}
                        composable(Routes.WORKOUT_DETAIL.name+"/{workout_id}",
                            arguments = listOf(
                                navArgument("workout_id"){
                                    type = NavType.LongType
                                })
                        ){backStackEntry ->
                            val id = backStackEntry.arguments?.getLong("workout_id")!!

                            WorkoutDetailScreen(navController = navController, workoutId = id)}
                    }
                }
            }
        }
    }
}