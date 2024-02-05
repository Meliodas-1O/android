package fr.uha.hassenforder.team.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import fr.uha.hassenforder.team.model.ExerciseWorkoutAssociation
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.repository.ExerciseViewModel
import fr.uha.hassenforder.team.repository.ExerciseWorkoutAssociationViewModel
import fr.uha.hassenforder.team.repository.WorkoutViewModel
import fr.uha.hassenforder.team.ui.components.ExerciceCard
import fr.uha.hassenforder.team.ui.components.SearchBar
import fr.uha.hassenforder.team.ui.theme.NavyBlue

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListScreen(
    exerciseViewModel: ExerciseViewModel = hiltViewModel(),
    ewasViewModel: ExerciseWorkoutAssociationViewModel = hiltViewModel(),
    navController: NavController,
    source: String,
    id: String,
    name: String?
) {
    var searchText by remember { mutableStateOf("") }

    var onDelete: (() -> Unit)

    val title:String
    val items :  List<Exercise>;
    val action : () -> Unit
    if(source == "TYPE"){
        val type : ExerciseType = ExerciseType.valueOf(id)
        val exerciseStates = exerciseViewModel.getExercisesByType(type).collectAsState(initial = emptyList())
        val workoutExercise = exerciseStates.value
        title = id
        items = workoutExercise
        action = {
            navController.navigate(Routes.EXERCISE_CREATION.name)
        }
    }else{
        val workoutId: Long = id.toLong()
        val exerciseStates = exerciseViewModel.getExercisesForWorkout(workoutId).collectAsState(initial = emptyList())
        val workoutExercise = exerciseStates.value
        title = name ?: "--"
        items = workoutExercise
        action = {
            navController.navigate(Routes.WORKOUT_DETAIL.name+"/$workoutId")
        }
    }
    if (items == null )return Text("hola")
    val filteredExercises = remember(items, searchText) {
        items.filter { exercise ->
            exercise.exerciseName.startsWith(searchText, ignoreCase = true)
        }
    }
    Scaffold(
        containerColor = NavyBlue,
        modifier = Modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue
                ),
                title = { Text(text = title.uppercase(), color = Color.White)},
                modifier = Modifier.background(Color(0xED0A0F3D)),
                actions = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(Icons.Default.SportsGymnastics, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color(0xFF002185),
                contentColor = Color.White,
                onClick = action,
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
                SearchBar(
                    label = "Search exercises...",
                    searchText = searchText,
                    onSearchTextChange = { searchText = it }
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(filteredExercises) { exercise ->
                        if(source=="TYPE"){
                            ExerciceCard(exercise = exercise, navController = navController, onDelete = {exerciseViewModel.deleteExercise(exercise) })
                        }else{
                            val association = ExerciseWorkoutAssociation(eid=exercise.exerciseId, wid = id.toLong())
                            ExerciceCard(exercise = exercise, navController = navController, onDelete = {ewasViewModel.deleteAssociation(association)})
                        }

                    }
                }
            }
        }
    )
}

