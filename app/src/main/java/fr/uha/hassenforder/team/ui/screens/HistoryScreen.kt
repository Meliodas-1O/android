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
import androidx.navigation.NavController
import fr.uha.hassenforder.team.model.Workout
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.ui.components.CustomCard
import fr.uha.hassenforder.team.ui.theme.NavyBlue
import fr.uha.hassenforder.team.ui.theme.ShadowBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(workoutList: List<Workout>, navController: NavController) {
    Scaffold(
        containerColor = NavyBlue,
        modifier = Modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue
                ),
                title = { Text(text = "History", color = Color.White)},
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
                            workoutList.groupBy { it.date.toString() }.toList()
                        ) { (date, sessionsForDate) ->
                            Text(
                                color = Color.White,
                                text = date,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(16.dp)
                            )
                            sessionsForDate.forEach { session ->
                                SessionItem(workout = session, navController = navController)
                            }
                            Divider()
                        }
                    }
        }
    )
}

@Composable
fun SessionItem(workout: Workout, navController: NavController) {
    CustomCard(
        title = "Session Name: ${workout.workoutName}",
        content =  {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Duration: ${workout.workoutDuration}",color = Color.White)
                Text(text = "Calories Burned: ${workout.caloriesBurned}",color = Color.White)
            }
        },
        cornerRadius = 13.dp,
        elevation = 5.dp,
        shadowColor = ShadowBlue,
        cardHeight = 130.dp,
        onClick = {
            navController.navigate(Routes.LIST_EXERCISE.name + "/${workout.workoutId}")
        }
    )
}