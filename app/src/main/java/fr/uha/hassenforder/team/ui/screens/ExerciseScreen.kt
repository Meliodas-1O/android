package fr.uha.hassenforder.team.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.ui.components.OutlinedSpinnerFieldEnum

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable

fun ExerciseScreen(navController: NavController) {
    var exerciseName by remember { mutableStateOf("") }
    var exerciseType by remember { mutableStateOf(ExerciseType.CARDIO) }
    var exerciseDuration by remember { mutableStateOf("") }
    var exerciseImage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val exerciseTypes = ExerciseType.values().map { it.name }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Exercise") },
                actions = {
                    IconButton(
                        onClick = {
                            // Handle form submission (you can replace this with your logic)
                            val newExercise = Exercise(
                                name = exerciseName,
                                type = exerciseType,
                                duration = exerciseDuration,
                                image = exerciseImage
                            )
                            // Print the new exercise details for demonstration
                            println(newExercise)

                            // Clear the form fields
                            exerciseName = ""
                            exerciseType = ExerciseType.CARDIO
                            exerciseDuration = ""
                            exerciseImage = ""

                            // Hide the keyboard
                            keyboardController?.hide()
                            navController.navigate(Routes.EXERCISE.name)
                        }
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Send")
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
                onValueChange = { exerciseName = it },
                label = { Text("Exercise Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                    }
                )
            )
            OutlinedSpinnerFieldEnum(
                value = game.genreState.current,
                onValueChange = { uiCB.onEvent(GameViewModel.UIEvent.GenreChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                enumValues = Genre.values(),
                labelId = R.string.genre,
                errorId = game.genreState.errorId
            )
            // Display the selected type
            Text(text = "Selected Type: ${exerciseType.name}")

            // Exercise Duration
            OutlinedTextField(
                value = exerciseDuration,
                onValueChange = { exerciseDuration = it },
                label = { Text("Exercise Duration") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        // Move focus to the next field or handle as needed
                    }
                )
            )

            // Exercise Image
            OutlinedTextField(
                value = exerciseImage,
                onValueChange = { exerciseImage = it },
                label = { Text("Exercise Image URL") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Submit the form or handle as needed
                        keyboardController?.hide()
                    }
                )
            )
        }
    }
}