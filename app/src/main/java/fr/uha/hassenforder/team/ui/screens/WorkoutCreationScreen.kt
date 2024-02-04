package fr.uha.hassenforder.team.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseWorkoutAssociation
import fr.uha.hassenforder.team.model.Workout
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.repository.ExerciseViewModel
import fr.uha.hassenforder.team.repository.ExerciseWorkoutAssociationRepository
import fr.uha.hassenforder.team.repository.ExerciseWorkoutAssociationViewModel
import fr.uha.hassenforder.team.repository.WorkoutViewModel
import java.util.*
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateWorkoutScreen(
    ewasViewModel: ExerciseWorkoutAssociationViewModel = hiltViewModel(),
    workoutViewModel: WorkoutViewModel = hiltViewModel(), // Use the correct view model here
    exerciseViewModel: ExerciseViewModel = hiltViewModel(),
    navController: NavController
) {
    var workoutName by remember { mutableStateOf("") }
    var workoutDuration by remember { mutableStateOf("") }
    var caloriesBurned by remember { mutableStateOf("") }
    val selectedExercises = remember { mutableStateListOf<Exercise>() }
    val exerciseStates = exerciseViewModel.exercises.collectAsState(initial = emptyList())
    val workoutExercise = exerciseStates.value
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Workout") },
                navigationIcon = {
                    // Handle back navigation
                    Button(onClick = { navController.navigateUp() }) {
                        Text("Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            OutlinedTextField(
                value = workoutName,
                onValueChange = { workoutName = it },
                label = { Text("Workout Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(
                value = workoutDuration,
                onValueChange = { workoutDuration = it },
                label = { Text("Workout Duration") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(
                value = caloriesBurned,
                onValueChange = { caloriesBurned = it },
                label = { Text("Calories Burned") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Button(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Add Exercises")
            }
            Button(
                onClick = {
                    val workout = Workout(
                        workoutId =(1..10000000000000).random().toLong(),
                        workoutName = workoutName,
                        workoutDuration = workoutDuration,
                        caloriesBurned = caloriesBurned.toIntOrNull() ?: 0,
                        date = Date()
                    )
                    workoutViewModel.insertWorkout(workout)
                    val associations = selectedExercises.map { exercise ->
                        ExerciseWorkoutAssociation(
                            eid = exercise.exerciseId,
                            wid = workout.workoutId
                        )
                    }
                    ewasViewModel.insertAllAssociations(associations)
                    navController.navigate(Routes.WORKOUT.name)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Create Workout")
            }
            // Dialog to select exercises
            if (showDialog) {
                ExerciseSelectionDialog(
                    exercises = workoutExercise,
                    selectedExercises = selectedExercises,
                    onExerciseSelected = { exercise ->
                        selectedExercises.add(exercise)
                    },
                    onDialogDismiss = { showDialog = false }
                )
            }
        }
    }
}
@Composable
fun ExerciseSelectionDialog(
    exercises: List<Exercise>,
    selectedExercises: List<Exercise>,
    onExerciseSelected: (Exercise) -> Unit,
    onDialogDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDialogDismiss() },
        title = { Text("Select Exercises") },
        confirmButton = {
            Button(onClick = { onDialogDismiss() }) {
                Text("Close")
            }
        },
        text = {
            LazyColumn {
                items(exercises) { exercise ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        Checkbox(
                            checked = selectedExercises.contains(exercise),
                            onCheckedChange = { isChecked ->
                                if (isChecked) {
                                    onExerciseSelected(exercise)
                                }
                            }
                        )
                        Text(
                            text = exercise.exerciseName,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    )
}

