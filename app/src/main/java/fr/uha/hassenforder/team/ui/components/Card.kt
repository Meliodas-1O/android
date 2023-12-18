package fr.uha.hassenforder.team.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.uha.hassenforder.team.ui.theme.NavyBlue

@Composable
fun CustomCard(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = NavyBlue,
    cornerRadius: Dp = 0.dp,
    elevation: Dp = 0.dp,
    shadowColor: Color = Color.Black,
) {
    val m = MaterialTheme.colorScheme.primary
    Card(
        modifier = modifier
            .background(color = backgroundColor)
            .shadow(elevation, shape = RoundedCornerShape(cornerRadius), spotColor = shadowColor)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .background(color = backgroundColor)
                .padding(16.dp)
                .clickable(
                    onClick = { Log.d("Bro", "$m") }
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
                Text(
                    text = content,
                    style = typography.bodyLarge,
                )
            }
        }
    }
}
