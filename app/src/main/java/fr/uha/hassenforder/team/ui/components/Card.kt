package fr.uha.hassenforder.team.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomCard(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xED0A0F3D),
    cornerRadius: Dp = 8.dp,
    elevation: Dp = 9.dp,
    shadowColor: Color = Color.Gray,
    cardHeight: Dp = 200.dp
) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            backgroundColor,
            backgroundColor.copy(alpha = 0.7f)
        )
    )

    Card(
        modifier = modifier
            .padding(16.dp)
            .background(brush = gradient)
            .shadow(elevation, shape = RoundedCornerShape(cornerRadius), spotColor = shadowColor)
            .fillMaxWidth(0.95f)
    ) {
        Column(
            modifier = Modifier
                .background(brush = gradient)
                .padding(16.dp)
                .height(cardHeight)
                .clickable(
                    onClick = { /* Handle click */ }
                )
                .fillMaxWidth(),
        ) {
            Text(
                text = title,
                style = typography.bodyLarge,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            content()
        }
    }
}
