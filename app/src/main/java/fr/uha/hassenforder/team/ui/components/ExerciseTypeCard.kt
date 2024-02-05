package fr.uha.hassenforder.team.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.uha.hassenforder.team.navigation.Routes


@Composable
fun ExerciseTypeCard(navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DualCardRow(
            card1 = {
                CustomCard(
                    title = "Cardio",
                    content = {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "Augmente la fréquence cardiaque",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    /*    Image(
                            painter = painterResource(id = R.drawable.cardio),
                            contentDescription = "My Image",
                            modifier = Modifier
                                .fillMaxSize()
                        ) */
                    },
                    onClick = {
                        navController.navigate(Routes.LIST_EXERCISE.name+"/TYPE/CARDIO")
                    }

                ) {}
            },
            card2 = {
                CustomCard(
                    title = "Strength",
                    content = {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "Développe la force et la masse musculaire",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )}
                    },
                    onClick = {
                        navController.navigate(Routes.LIST_EXERCISE.name+"/TYPE/STRENGTH")
                    }

                ) {}
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
        DualCardRow(
            card1 = {
                CustomCard(
                    title = "Flexibility",
                    content = {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "Améliore la souplesse et la mobilité des articulations",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )}
                    },
                    onClick = {
                        navController.navigate(Routes.LIST_EXERCISE.name+"/TYPE/FLEXIBILITY")
                    }
                ) {}
            },
            card2 = {
                CustomCard(
                    title = "High-Intensity Interval Training",
                    content = {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "Entraînement par intervalles à haute intensité",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )}
                    },                    onClick = {
                        navController.navigate(Routes.LIST_EXERCISE.name+"/TYPE/HIGH_INTENSITY_INTERVAL_TRAINING")
                    }
                ) {}
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
        DualCardRow(
            card1 = {
                CustomCard(
                    title = "Core",
                    content = {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "Renforce les muscles du tronc",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )}
                    },                    onClick = {
                        navController.navigate(Routes.LIST_EXERCISE.name+"/TYPE/CORE")
                    }
                ) {}
            },
            card2 = {
                CustomCard(
                    title = "CrossFit",
                    content = {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "Améliore la condition physique générale",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )}
                    },                    onClick = {
                        navController.navigate(Routes.LIST_EXERCISE.name+"/TYPE/CROSSFIT")
                    }

                ) {}
            }
        )
    }
}
