package fr.uha.hassenforder.team.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.uha.hassenforder.team.model.Exercise

@Composable
fun ExerciseSelectionDialog(
    exercises: List<Exercise>,
    selectedExercises: List<Exercise>,
    onExerciseSelected: (Exercise) -> Unit,
    onDialogDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDialogDismiss() },
        title = { Text("Select Exercises") },
        confirmButton = {
            Button(onClick = { onDialogDismiss() }) {
                Text("Close")
            }
        },
        text = {
            LazyColumn {
                items(exercises) { exercise ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        Checkbox(
                            checked = selectedExercises.contains(exercise),
                            onCheckedChange = { isChecked ->
                                if (isChecked) {
                                    onExerciseSelected(exercise)
                                }
                            }
                        )
                        Text(
                            text = exercise.exerciseName,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    )
}
