package fr.uha.hassenforder.team


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.uha.hassenforder.team.ui.theme.LineChartComposeTheme


@Composable
fun LineChartWithTheme( data: List<Pair<String, Double>>) {
    LineChartComposeTheme {
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
