package fr.uha.hassenforder.team.ui.components

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import fr.uha.hassenforder.team.navigation.BottomNavigationItem
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.ui.theme.NavyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    navController: NavHostController
) {
    NavigationBar(containerColor = NavyBlue) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                label = { Text(text = item.title, color = Color.White)},
                onClick = {
                    onItemSelected(index)
                    when (index) {
                        0 -> navController.navigate(Routes.HOME.name)
                        1 -> navController.navigate(Routes.EXERCISE.name)
                        2 -> navController.navigate(Routes.WORKOUT.name)
                        3 -> navController.navigate(Routes.HISTORY.name)
                    }
                },
                icon = {
                    BadgedBox(badge = {
                        if (item.badgeCount != null) {
                            Badge {
                                Text(text = item.badgeCount.toString())
                            }
                        } else if (item.hasNews) {
                            Badge()
                        }
                    }) {
                        Icon(
                            imageVector = if (selectedItemIndex == index) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }
}