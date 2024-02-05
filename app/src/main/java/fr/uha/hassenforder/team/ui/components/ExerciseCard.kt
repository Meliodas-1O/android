package fr.uha.hassenforder.team.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.repository.ExerciseViewModel
import fr.uha.hassenforder.team.ui.theme.ShadowBlue


@Composable
fun ExerciceCard(
    exerciseViewModel: ExerciseViewModel = hiltViewModel(),
    exercise: Exercise,
    navController: NavController,
    onDelete: (() -> Unit)
) {
    SwipeableItem (
        onEdit = {navController.navigate(Routes.EXERCISE_DETAIL.name+"/${exercise.exerciseId}")},
        onDelete = onDelete) {
        CustomCard(
            onClick = {navController.navigate(Routes.EXERCISE_DETAIL.name+"/${exercise.exerciseId}")},
            title = exercise.exerciseName,
            content = {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Text(text = exercise.exerciseName, style = MaterialTheme.typography.bodyLarge, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = exercise.exerciseType.name, style = MaterialTheme.typography.bodyLarge, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Duration: ${exercise.exerciseDuration}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            },
            cornerRadius = 13.dp,
            elevation = 5.dp,
            shadowColor = ShadowBlue
        )
    }

}
