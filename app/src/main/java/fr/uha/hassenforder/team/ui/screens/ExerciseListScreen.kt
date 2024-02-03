package fr.uha.hassenforder.team.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.uha.hassenforder.team.navigation.Routes
import fr.uha.hassenforder.team.ui.components.CustomCard
import fr.uha.hassenforder.team.ui.components.DualCardRow
import fr.uha.hassenforder.team.ui.components.SearchBar
import fr.uha.hassenforder.team.ui.theme.NavyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    Scaffold(
         containerColor = NavyBlue,
         modifier = Modifier,
         topBar = {
             TopAppBar(
                 colors = TopAppBarDefaults.topAppBarColors(
                     containerColor = NavyBlue,
                     scrolledContainerColor = NavyBlue
                 ),
                 title = { Text(text = "Exercises") },
                 modifier = Modifier.background(Color(0xED0A0F3D)),
                 actions = {
                     IconButton(
                         onClick = { /* Handle navigation to notifications */ }
                     ) {
                         Icon(Icons.Default.SportsGymnastics, contentDescription = null)
                     }
                 }
             )
         },
         floatingActionButton = {
             FloatingActionButton(
                 containerColor = Color(0xFF002185),
                 onClick = {
                     navController.navigate(Routes.EXERCISE_CREATION.name)
                 },
                 modifier = Modifier
                     .padding(16.dp)
                     .background(Color(0xED0A0F3D), shape = RoundedCornerShape(16.dp))

             ) {
                 Icon(Icons.Default.Add, contentDescription = "Add Exercise")
             }
         },
         content = { innerPadding ->
             LazyColumn(
                 modifier = Modifier
                     .fillMaxSize()
                     .padding(innerPadding)
             ) {
                 item{
                     SearchBar(
                         searchText = searchText,
                         onSearchTextChange = { searchText = it }
                     )
                     Spacer(modifier = Modifier.height(16.dp))
                 }
                 item {
                     ExerciseTypeCard()
                     Spacer(modifier = Modifier.height(16.dp))
                 }
             }
         }
     )
 }

@Composable
fun ExerciseTypeCard(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DualCardRow(
            card1 = {
                CustomCard(
                    title = "Cardio",
                    content = {}
                )
            },
            card2 = {
                CustomCard(
                    title = "Strength",
                    content = {}
                )
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
        DualCardRow(
            card1 = {
                CustomCard(
                    title = "Flexibility",
                    content = {}
                )
            },
            card2 = {
                CustomCard(
                    title = "High-Intensity Interval Training",
                    content = {}
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        DualCardRow(
            card1 = {
                CustomCard(
                    title = "Core",
                    content = {}
                )
            },
            card2 = {
                CustomCard(
                    title = "CrossFit",
                    content = {}
                )
            }
        )
    }
}