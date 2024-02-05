package fr.uha.hassenforder.team.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import fr.uha.hassenforder.team.helpers.Utils.calculateWorkoutCalories
import fr.uha.hassenforder.team.helpers.Utils.calculateWorkoutDuration
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseWorkoutAssociation
import fr.uha.hassenforder.team.model.Workout
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.repository.ExerciseViewModel
import fr.uha.hassenforder.team.repository.ExerciseWorkoutAssociationViewModel
import fr.uha.hassenforder.team.repository.WorkoutViewModel
import fr.uha.hassenforder.team.ui.components.ExerciseSelectionDialog
import fr.uha.hassenforder.team.ui.theme.NavyBlue
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDetailScreen(
    ewasViewModel: ExerciseWorkoutAssociationViewModel = hiltViewModel(),
    workoutViewModel: WorkoutViewModel = hiltViewModel(),
    exerciseViewModel: ExerciseViewModel = hiltViewModel(),
    navController: NavController,
    workoutId: Long // Add the workoutId parameter for updating the specific workout
) {

    var workoutName by remember { mutableStateOf("") }
    var workoutDuration by remember { mutableStateOf(0) }
    var caloriesBurned by remember { mutableStateOf(0) }
    val selectedExercises = remember { mutableStateListOf<Exercise>() }
    val exerciseStates = exerciseViewModel.exercises.collectAsState(initial = emptyList())
    val workoutExercise = exerciseStates.value
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(Date()) }

    val calendarState = rememberSheetState()
    CalendarDialog(state = calendarState, selection = CalendarSelection.Date { date ->
        selectedDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
    })
    val defaultWorkout = Workout(workoutId = 0L, workoutName = "", date = Date())
    val workoutDetails by workoutViewModel.getWorkoutById(workoutId).collectAsState(initial = defaultWorkout)

    workoutDetails.let { workout ->
        workoutName = workout.workoutName
        selectedDate = workout.date
    }

    Scaffold(
        containerColor = NavyBlue,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue
                ),
                title = { Text("Update Workout" ,color = Color.White) },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            OutlinedTextField(
                textStyle = TextStyle(color = Color.White) ,
                value = workoutName,
                onValueChange = { workoutName = it },
                label = { Text("Workout Name", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )/*
            OutlinedTextField(
                textStyle = TextStyle(color = Color.White) ,
                readOnly = true,
                value = caloriesBurned.toString(),
                onValueChange = { /* caloriesBurned is calculated and cannot be changed manually */ },
                label = { Text("Calories Burned", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )*/
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                OutlinedTextField(
                    textStyle = TextStyle(color = Color.White) ,
                    readOnly = true,
                    value = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(selectedDate),
                    onValueChange = { /* selectedDate is set by the calendar dialog */ },
                    label = { Text("Selected Date", color = Color.White) },
                    modifier = Modifier
                        .padding(16.dp)
                )
                IconButton(
                    onClick = { calendarState.show() },
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Calendar Icon",
                    )
                }
            }

            Button(
                colors= ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF002185),
                    contentColor = Color.White,
                ),
                onClick = {
                    showDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Add Exercises", color = Color.White)
            }
            Button(
                colors= ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF002185),
                    contentColor = Color.White,
                ),
                onClick = {
                    caloriesBurned = calculateWorkoutCalories(selectedExercises)
                    workoutDuration = calculateWorkoutDuration(selectedExercises)
                    val workout = Workout(
                        workoutId = workoutId, // Update the existing workoutId
                        workoutName = workoutName,
                        date = selectedDate
                    )
                    workoutViewModel.updateWorkout(workout) // Use updateWorkout instead of insertWorkout
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
                Text("Update Workout", color = Color.White)
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
