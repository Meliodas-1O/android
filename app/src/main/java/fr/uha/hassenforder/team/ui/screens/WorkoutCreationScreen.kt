package fr.uha.hassenforder.team.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import fr.uha.hassenforder.team.R
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
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateWorkoutScreen(
    ewasViewModel: ExerciseWorkoutAssociationViewModel = hiltViewModel(),
    workoutViewModel: WorkoutViewModel = hiltViewModel(),
    exerciseViewModel: ExerciseViewModel = hiltViewModel(),
    navController: NavController
) {

    var workoutName by remember { mutableStateOf("") }
    var workoutDuration by remember { mutableStateOf(0) }
    var caloriesBurned by remember { mutableStateOf(0) }
    val selectedExercises = remember { mutableStateListOf<Exercise>() }
    val exerciseStates = exerciseViewModel.exercises.collectAsState(initial = emptyList())
    val workoutExercise = exerciseStates.value
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(Date()) }

    var calendarState = rememberSheetState()
    CalendarDialog(state = calendarState, selection =CalendarSelection.Date{date ->
        selectedDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
    } )
    Scaffold(
        containerColor = NavyBlue,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue
                ),
                title = { Text("Create Workout",color = Color.White) },
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
                label = { Text("Workout Name" ,color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(
                textStyle = TextStyle(color = Color.White) ,
                value = workoutDuration.toString(),
                onValueChange = { workoutDuration = it.toInt() },
                label = { Text("Workout Duration",color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(
                textStyle = TextStyle(color = Color.White) ,
                readOnly = true,
                value = caloriesBurned.toString(),
                onValueChange = { caloriesBurned = it.toInt() },
                label = { Text("Calories Burned",color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                OutlinedTextField(
                    textStyle = TextStyle(color = Color.White) ,
                    readOnly = true,
                    value = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(selectedDate),
                    onValueChange = {selectedDate = SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(it) ?: selectedDate },
                    label = { Text("Selected Date",color = Color.White) },
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
                Text("Add Exercises")
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
                        workoutId =(1..10000000000000).random().toLong(),
                        workoutName = workoutName,
                        date = selectedDate
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
