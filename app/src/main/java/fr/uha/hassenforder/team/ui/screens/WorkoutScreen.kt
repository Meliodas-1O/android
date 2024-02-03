package fr.uha.hassenforder.team.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import fr.uha.hassenforder.team.model.Workout
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.ui.components.CustomCard
import fr.uha.hassenforder.team.ui.theme.NavyBlue
import fr.uha.hassenforder.team.ui.theme.ShadowBlue
import java.util.Date


val sessions = listOf(
    Workout(
        workoutId =1,
        workoutName = "Morning Workout",
        caloriesBurned = 200,
        date = Date(3423),
        workoutDuration = "13 mn"
    ),
    // Add more sessions
    Workout(
        workoutId =3,
        workoutDuration = "13 mn",
        workoutName = "Afternoon Run",
        caloriesBurned = 250,
        date = Date(3423),
    )
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(navController: NavController, workoutId: Long) {

    val workoutExercise = sessions.find{ it.workoutId == workoutId }
    if (workoutExercise == null )return Text("hola")
    Scaffold(
        containerColor = NavyBlue,
        modifier = Modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue
                ),
                title = { Text(text = workoutExercise.workoutName, color = Color.White)},
                modifier = Modifier.background(Color(0xED0A0F3D)),
                actions = {
                    IconButton(
                        onClick = { /* Handle navigation to notifications */ }
                    ) {
                        Icon(Icons.Default.SportsGymnastics, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color(0xFF002185),
                onClick = {
                    navController.navigate(Routes.WORKOUT_CREATION.name)
                },
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Exercise")
            }
        },
        content = { innerPadding ->
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
            ){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(sessions) { exercise ->
                        ExerciceCard(exercise = Exercise(1,"running_image", ExerciseType.CARDIO, "30 minutes", ), navController = navController)
                    }
                }
            }
        }
    )
}


@Composable
fun ExerciceCard(exercise: Exercise, navController: NavController) {
    CustomCard(
        onClick = {navController.navigate(Routes.EXERCISE_DETAIL.name+"/exercice_id")},
        title = exercise.exerciseName,
        content = {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(text = exercise.exerciseName, style = MaterialTheme.typography.bodyLarge, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = exercise.exerciseType.name, style = MaterialTheme.typography.bodyLarge, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Duration: ${exercise.exerciseDuration}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        },
        cornerRadius = 13.dp,
        elevation = 5.dp,
        shadowColor = ShadowBlue
    )
}
