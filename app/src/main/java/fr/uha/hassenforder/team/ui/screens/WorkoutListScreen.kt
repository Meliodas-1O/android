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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import fr.uha.hassenforder.team.model.Workout
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.ui.components.SearchBar
import fr.uha.hassenforder.team.ui.theme.NavyBlue
import java.time.LocalDate
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    val workoutList = listOf(
        Workout(
            workoutId=2,
            workoutName = "Morning Workout",
            workoutDuration = "1 hour",
            caloriesBurned = 300,
            date = Date(14),
        ),
        Workout(
            workoutId=3,
            workoutName = "Evening Routine",
            workoutDuration = "45 mins",
            caloriesBurned = 250,
            date = Date(14),
        ),
        Workout(
            workoutId=6,
            workoutName = "Weekend Workout",
            workoutDuration = "1.5 hours",
            caloriesBurned = 400,
            date = Date(14),
        )
    )

    Scaffold(
        containerColor = NavyBlue,
        modifier = Modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue
                ),
                title = { Text(text = "Workout", color = Color.White)},
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
                SearchBar(
                    label = "Search workout",
                    searchText = searchText,
                    onSearchTextChange = { searchText = it }
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(workoutList) { workout ->
                        SessionItem(workout = workout, navController = navController)
                    }
                }
            }
        }
    )
}

