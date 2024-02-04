package fr.uha.hassenforder.team.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.repository.ExerciseViewModel
import fr.uha.hassenforder.team.ui.components.ExerciseTypeDropdown
import fr.uha.hassenforder.team.ui.theme.NavyBlue
import kotlin.random.Random

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable

fun ExerciseScreen(
    exerciseViewModel: ExerciseViewModel = hiltViewModel(),
    navController: NavController) {
    var exerciseName by remember { mutableStateOf("") }
    var exerciseType by remember { mutableStateOf(ExerciseType.CARDIO) }
    var exerciseDuration by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        containerColor = NavyBlue,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue
                ),
                title = { Text("Create Exercise", color = Color.White) },
                actions = {
                    IconButton(
                        onClick = {
                            // Handle form submission (you can replace this with your logic)
                            val newExercise = Exercise(
                                exerciseName = exerciseName,
                                exerciseType = exerciseType,
                                exerciseDuration = exerciseDuration,
                            )
                            exerciseViewModel.insertExercise(newExercise)
                            println(newExercise)

                            exerciseName = ""
                            exerciseType = ExerciseType.CARDIO
                            exerciseDuration = ""

                            // Hide the keyboard
                            keyboardController?.hide()
                            navController.navigate(Routes.EXERCISE.name)
                        }
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Send", tint = Color.White)
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
                textStyle = TextStyle(color = Color.White) ,// Set your desired text color
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
                textStyle = TextStyle(color = Color.White) ,// Set your desired text color
                value = exerciseDuration,
                singleLine = true,
                onValueChange = { exerciseDuration = it },
                label = { Text("Exercise Duration",  color= Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )


            Button(
                colors= ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF002185),
                    contentColor = Color.White,
                ),
                onClick = {
                    // Handle form submission (you can replace this with your logic)
                    val newExercise = Exercise(
                        exerciseName = exerciseName,
                        exerciseType = exerciseType,
                        exerciseDuration = exerciseDuration,
                    )
                    println(newExercise)
                    exerciseViewModel.insertExercise(newExercise)

                    // Clear the form fields
                    exerciseName = ""
                    exerciseType = ExerciseType.CARDIO
                    exerciseDuration = ""

                    // Hide the keyboard
                    keyboardController?.hide()
                    navController.navigate(Routes.EXERCISE.name)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                content = {Text("Create Exercise")}
            )
        }
    }
}