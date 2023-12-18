package fr.uha.hassenforder.team


import android.graphics.Point
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.uha.hassenforder.team.ui.components.CustomCard
import fr.uha.hassenforder.team.ui.theme.NavyBlue
import fr.uha.hassenforder.team.ui.theme.ShadowBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(message: String) {
    val motivationText = "Stay motivated and make progress every day!"
    val caloriesBurned = 500
    val nextSessionDate = "Monday, Jan 1"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Gym App") },
                actions = {
                    IconButton(
                        onClick = { /* Handle navigation to notifications */ }
                    ) {
                        Icon(Icons.Default.FitnessCenter, contentDescription = null)
                    }
                }
            )
        },
        content = {
                innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                item {
                    MotivationCard(title = "Motivation", text = motivationText)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    CaloriesBurnedCard(title = "Calories Burned today", caloriesBurned = caloriesBurned)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    CaloriesGraph(title= "Calories Burned Variation", description = "TODO")
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    NextSessionCard(title = "Next session", date = nextSessionDate)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )
}

@Composable
fun MotivationCard(title: String, text: String) {
    CustomCard(
        title = title,
        content = text,
        cornerRadius = 13.dp,
        elevation = 5.dp,
        shadowColor = ShadowBlue
    )
}

@Composable
fun CaloriesBurnedCard(title:String, caloriesBurned: Int) {
    CustomCard(
        title = title,
        content = "$caloriesBurned Cal",
        cornerRadius = 13.dp,
        elevation = 5.dp,
        shadowColor = ShadowBlue
    )
}

@Composable
fun CaloriesGraph(title: String, description: String) {
    CustomCard(
        title = title,
        content = description,
        cornerRadius = 13.dp,
        elevation = 5.dp,
        shadowColor = ShadowBlue
    )
}

@Composable
fun NextSessionCard(title: String, date: String) {
    CustomCard(
        title = title,
        content = date,
        cornerRadius = 13.dp,
        elevation = 5.dp,
        shadowColor = ShadowBlue
    )
}
