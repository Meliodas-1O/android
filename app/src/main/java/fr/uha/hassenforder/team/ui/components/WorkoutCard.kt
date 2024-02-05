package fr.uha.hassenforder.team.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.uha.hassenforder.team.model.Workout
import fr.uha.hassenforder.team.model.WorkoutDomain
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.repository.WorkoutViewModel
import fr.uha.hassenforder.team.ui.theme.ShadowBlue

@Composable
fun SessionItem(
    workoutViewModel: WorkoutViewModel = hiltViewModel(),
    workout: WorkoutDomain,
    navController: NavController
) {
    val workoutDB = workout.workout
    SwipeableItem(onDelete ={workoutViewModel.deleteWorkout(workoutDB)
    },
        onEdit = {
            navController.navigate(Routes.WORKOUT_DETAIL.name+"/${workoutDB.workoutId}")
        }
    ) {
        CustomCard(
            title = "Session Name: ${workoutDB.workoutName}",
            content =  {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {/*$  */
                    Text(text = "Duration: ${workout.duration}",color = Color.White)
                    Text(text = "Calories Burned: ${workout.caloriesBurned} ",color = Color.White)
                }
            },
            cornerRadius = 13.dp,
            elevation = 5.dp,
            shadowColor = ShadowBlue,
            cardHeight = 130.dp,
            onClick = {
                val idToString: String = workoutDB.workoutId.toString();
                navController.navigate(Routes.LIST_EXERCISE.name+"/WORKOUT/$idToString?name=${workoutDB.workoutName}")
            }
        )
    }

}
