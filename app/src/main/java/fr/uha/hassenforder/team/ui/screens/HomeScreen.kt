package fr.uha.hassenforder.team


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.uha.hassenforder.team.model.Workout
import fr.uha.hassenforder.team.ui.components.CustomCard
import fr.uha.hassenforder.team.ui.components.DualCardRow
import fr.uha.hassenforder.team.ui.theme.NavyBlue
import fr.uha.hassenforder.team.ui.theme.ShadowBlue
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun HomeScreen(
    caloriesBurnedPerDay :  List<Pair<String, Double>>,
    nextWorkout : Workout?,
    calories : Int,
    ) {

    val motivationText = "Stay motivated and make progress every day!"
    val nextSessionDate = nextWorkout?.workoutName ?: "No Session"

    Log.d("HOLAAAAAAA", caloriesBurnedPerDay.toString())
    Scaffold(
        containerColor = NavyBlue,
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue
                ),
                title = { Text(text = "Gym App",color = Color.White) },
                modifier = Modifier.background(Color(0xED0A0F3D)),
                actions = {
                    IconButton(
                        onClick = {}
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
                    MotivationCard(title = "Motivation", text = { Text(motivationText,color = Color.White) })
                }

                item {

                    NextSessionCard(title = "Next Session", date = { Text(text = nextSessionDate, color = Color.White) })
                    /*CalorieAndNextSession(
                        caloriesTitle = "Calories burned",
                        caloriesBurned = { Text(text = "$calories Cal",color = Color.White) },
                        titleNextSession = "Next Session",
                        date = { Text(text = nextSessionDate, color = Color.White) },
                    )*/
                }

                item {
                    CaloriesGraph(title= "Calories Burned Variation", chart = { LineChartWithTheme(caloriesBurnedPerDay)})
                }
            }
        }
    )
}

@Composable
fun MotivationCard(title: String, text: @Composable () -> Unit) {
    CustomCard(
        title = title,
        content = text,
        cornerRadius = 13.dp,
        elevation = 5.dp,
        shadowColor = ShadowBlue,
        cardHeight = 100.dp,
        onClick = {}
    ) {}
}

@Composable
fun CaloriesBurnedCard(title:String, caloriesBurned: @Composable () -> Unit) {
    CustomCard(
        title = title,
        content = caloriesBurned,
        cornerRadius = 13.dp,
        elevation = 5.dp,
        shadowColor = ShadowBlue,
        cardHeight = 100.dp,
        onClick = {}

    ) {}
}

@Composable
fun CalorieAndNextSession(caloriesTitle: String, caloriesBurned: @Composable () -> Unit, titleNextSession: String, date : @Composable () -> Unit){
    DualCardRow(card1 = { CaloriesBurnedCard(title = caloriesTitle, caloriesBurned = caloriesBurned) }, card2 = { NextSessionCard(title = titleNextSession, date = date) })
}


@Composable
fun CaloriesGraph(title: String, chart: @Composable () -> Unit
) {
    CustomCard(
        title = title,
        content = chart,
        cornerRadius = 13.dp,
        elevation = 5.dp,
        shadowColor = ShadowBlue,
        cardHeight = 250.dp,
        onClick = {}
    ) {}
}

@Composable
fun NextSessionCard(title: String, date: @Composable () -> Unit) {
    CustomCard(
        title = title,
        content = date,
        cornerRadius = 13.dp,
        elevation = 5.dp,
        shadowColor = ShadowBlue,
        cardHeight = 100.dp,
        onClick = {}
    ) {}
}
