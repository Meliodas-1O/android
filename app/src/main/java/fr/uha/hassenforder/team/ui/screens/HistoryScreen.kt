package fr.uha.hassenforder.team.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.uha.hassenforder.team.model.Workout
import fr.uha.hassenforder.team.ui.theme.NavyBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(workouts: List<Workout>, modifier: Modifier = Modifier) {
    Scaffold(
        containerColor = NavyBlue,
        modifier = Modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue
                ),
                title = { Text(text = "History") },
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
        content = { innerPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ){
                        items(
                            workouts.groupBy { it.date }.toList()
                        ) { (date, sessionsForDate) ->
                            Text(
                                text = date,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(16.dp)
                            )

                            // Display cards for each session on that date
                            sessionsForDate.forEach { session ->
                                SessionItem(workout = session)
                                Divider() // Add a divider between sessions
                            }
                        }
                    }
        }
    )
}

@Composable
fun SessionItem(workout: Workout) {
    // You can customize the content here based on your needs
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Display session details in a Card
            Text(text = "Session Name: ${workout.name}", fontWeight = FontWeight.Bold)
            Text(text = "Duration: ${workout.duration}")
            Text(text = "Calories Burned: ${workout.caloriesBurned}")
            Text(text = "Number of Exercises: ${workout.exercises.size}")
        }
    }
}