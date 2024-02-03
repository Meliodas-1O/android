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
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import fr.uha.hassenforder.team.model.Workout
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.navigation.createBottomNavigationItems
import fr.uha.hassenforder.team.ui.components.CreateBottomNavigationBar
import fr.uha.hassenforder.team.ui.screens.ExerciseListScreen
import fr.uha.hassenforder.team.ui.screens.ExerciseScreen
import fr.uha.hassenforder.team.ui.screens.HistoryScreen
import fr.uha.hassenforder.team.ui.screens.WorkoutListScreen
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
        val sessions = listOf(
            Workout(
                name = "Morning Workout",
                duration = "30 minutes",
                caloriesBurned = 200,
                date = "2023-01-01",
                exercises = listOf(
                    Exercise("Running", ExerciseType.CARDIO, "15 minutes", "running_image"),
                    Exercise("Push-ups", ExerciseType.STRENGTH, "10 minutes", "pushups_image"),
                    Exercise("Yoga", ExerciseType.FLEXIBILITY, "5 minutes", "yoga_image")
                )
            ),
            Workout(
                name = "Evening Exercise",
                duration = "45 minutes",
                caloriesBurned = 300,
                date = "2023-01-02",
                exercises = listOf(
                    Exercise("Cycling", ExerciseType.CARDIO, "20 minutes", "cycling_image"),
                    Exercise("Weightlifting", ExerciseType.STRENGTH, "15 minutes", "weightlifting_image"),
                    Exercise("Stretching", ExerciseType.FLEXIBILITY, "10 minutes", "stretching_image")
                )
            ),
            // Add more sessions
            Workout(
                name = "Afternoon Run",
                duration = "40 minutes",
                caloriesBurned = 250,
                date = "2023-01-03",
                exercises = listOf(
                    Exercise("Running", ExerciseType.CARDIO, "30 minutes", "running_image"),
                    Exercise("Bodyweight Exercises", ExerciseType.STRENGTH, "10 minutes", "bodyweight_image")
                )
            ),
            Workout(
                name = "Afternoon Run",
                duration = "40 minutes",
                caloriesBurned = 250,
                date = "2023-01-03",
                exercises = listOf(
                    Exercise("Running", ExerciseType.CARDIO, "30 minutes", "running_image"),
                    Exercise("Bodyweight Exercises", ExerciseType.STRENGTH, "10 minutes", "bodyweight_image")
                )
            ),
            // Add more sessions
            Workout(
                name = "Full Body Workout",
                duration = "50 minutes",
                caloriesBurned = 350,
                date = "2023-01-04",
                exercises = listOf(
                    Exercise("High-Intensity Interval Training", ExerciseType.CARDIO, "20 minutes", "hiit_image"),
                    Exercise("Weightlifting", ExerciseType.STRENGTH, "20 minutes", "weightlifting_image"),
                    Exercise("Yoga", ExerciseType.FLEXIBILITY, "10 minutes", "yoga_image")
                )
            ),
            // Add more sessions
            Workout(
                name = "Indoor Cycling",
                duration = "35 minutes",
                caloriesBurned = 280,
                date = "2023-01-05",
                exercises = listOf(
                    Exercise("Cycling", ExerciseType.CARDIO, "30 minutes", "cycling_image"),
                    Exercise("Stretching", ExerciseType.FLEXIBILITY, "5 minutes", "stretching_image")
                )
            ),
            // Add more sessions
            Workout(
                name = "Strength Training",
                duration = "55 minutes",
                caloriesBurned = 400,
                date = "2023-01-06",
                exercises = listOf(
                    Exercise("Weightlifting", ExerciseType.STRENGTH, "40 minutes", "weightlifting_image"),
                    Exercise("Core Exercises", ExerciseType.STRENGTH, "15 minutes", "core_image")
                )
            ),
            // Add more sessions
            Workout(
                name = "Yoga and Meditation",
                duration = "40 minutes",
                caloriesBurned = 180,
                date = "2023-01-07",
                exercises = listOf(
                    Exercise("Yoga", ExerciseType.FLEXIBILITY, "30 minutes", "yoga_image"),
                    Exercise("Meditation",ExerciseType.FLEXIBILITY , "10 minutes", "meditation_image")
                )
            ),
            // Add more sessions
            Workout(
                name = "Cardio Blast",
                duration = "45 minutes",
                caloriesBurned = 320,
                date = "2023-01-08",
                exercises = listOf(
                    Exercise("Running", ExerciseType.CARDIO, "25 minutes", "running_image"),
                    Exercise("Jumping Jacks", ExerciseType.CARDIO, "10 minutes", "jumping_jacks_image"),
                    Exercise("Cycling", ExerciseType.CARDIO, "10 minutes", "cycling_image")
                )
            ),
            // Add more sessions
            Workout(
                name = "Functional Fitness",
                duration = "50 minutes",
                caloriesBurned = 370,
                date = "2023-01-09",
                exercises = listOf(
                    Exercise("Functional Exercises", ExerciseType.STRENGTH, "30 minutes", "functional_exercises_image"),
                    Exercise("Cardio", ExerciseType.CARDIO, "10 minutes", "cardio_image"),
                    Exercise("Stretching", ExerciseType.FLEXIBILITY, "10 minutes", "stretching_image")
                )
            ),
            // Add more sessions
            Workout(
                name = "Relaxation Workout",
                duration = "30 minutes",
                caloriesBurned = 150,
                date = "2023-01-10",
                exercises = listOf(
                    Exercise("Yoga", ExerciseType.FLEXIBILITY, "20 minutes", "yoga_image"),
                    Exercise("Meditation", ExerciseType.FLEXIBILITY, "10 minutes", "meditation_image")
                )
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
                        composable(Routes.HISTORY.name) { HistoryScreen(workouts = sessions)}
                        composable(Routes.EXERCISE_CREATION.name) { ExerciseScreen(navController) }
                        composable(Routes.WORKOUT_CREATION.name) { WorkoutScreen(navController) }
                        composable(Routes.NEXT_SESSIONS.name) { HistoryScreen(workouts = sessions)}

                    }
                }
            }
        }
    }
}