package fr.uha.hassenforder.team.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.ui.components.ExerciseTypeDropdown
import fr.uha.hassenforder.team.ui.theme.NavyBlue

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(navController: NavController, exercise: Exercise) {
    var exerciseName by remember { mutableStateOf(exercise.exerciseName) }
    var exerciseType by remember { mutableStateOf(exercise.exerciseType) }
    var exerciseDuration by remember { mutableStateOf(exercise.exerciseDuration) }

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        containerColor = NavyBlue,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue
                ),
                title = { Text("Exercise Details", color = Color.White) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                }
            )
        }
    ) { innerPadding ->
        // Content of the screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Exercise Name
            OutlinedTextField(
                value = exerciseName,
                singleLine = true,
                onValueChange = { exerciseName = it },
                label = { Text("Exercise Name", color= Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
            ExerciseTypeDropdown(
                selectedExerciseType = exerciseType,
                onExerciseTypeSelected = { newExerciseType ->
                    exerciseType = newExerciseType
                }
            )

            OutlinedTextField(
                value = exerciseDuration,
                singleLine = true,
                onValueChange = { exerciseDuration = it },
                label = { Text("Exercise Duration",  color= Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )


            // Button to update exercise details
            Button(
                colors= ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF002185),
                    contentColor = Color.White,
                ),
                onClick = {
                    // Handle updating exercise details (you can replace this with your logic)
                    val updatedExercise = Exercise(
                        exerciseName = exerciseName,
                        exerciseType = exerciseType,
                        exerciseDuration = exerciseDuration,
                    )
                    // Print the updated exercise details for demonstration
                    println(updatedExercise)

                    // Hide the keyboard
                    keyboardController?.hide()
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                content = { Text("Update Exercise") }
            )
        }
    }
}
