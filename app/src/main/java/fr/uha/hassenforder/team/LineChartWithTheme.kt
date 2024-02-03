package fr.uha.hassenforder.team


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.uha.hassenforder.team.ui.theme.LineChartComposeTheme

@Composable
fun LineChartWithTheme() {
    LineChartComposeTheme {
        val data = listOf(
            Pair("Sun", 111.45),
            Pair("Mon", 111.0),
            Pair("Tue", 113.45),
            Pair("Wed", 112.25),
            Pair("Thu", 116.45),
            Pair("Fri", 113.35),
            Pair("Sat", 118.65)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            LineChart(
                data = data,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .align(CenterHorizontally)
            )

        }
    }
}
