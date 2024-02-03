package fr.uha.hassenforder.team.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import fr.uha.hassenforder.team.model.ExerciseType

@Composable
fun ExerciseTypeDropdown(
    selectedExerciseType: ExerciseType,
    onExerciseTypeSelected: (ExerciseType) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density
    val dropdownWidth = with(LocalContext.current.resources.displayMetrics) { (240 * density).toInt() }

    OutlinedTextField(
        readOnly = true,
        value = selectedExerciseType.name,
        onValueChange = {},
        label = { Text("Select Exercise Type") },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        trailingIcon = {
            IconButton(onClick = { expanded = true }) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Expand")
            }
        },
        modifier = modifier
            .widthIn(max = dropdownWidth.dp)
            .padding(16.dp)
            .clickable {
                expanded = !expanded
            }
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.width(dropdownWidth.dp)
    ) {
        for (exerciseType in ExerciseType.values()) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onExerciseTypeSelected(exerciseType)
                },
                text = { Text(exerciseType.name) }
            )
        }
    }
}
