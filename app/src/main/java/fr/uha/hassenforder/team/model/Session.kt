package fr.uha.hassenforder.team.model

data class Session(
    val name: String,
    val duration: String,
    val caloriesBurned: Int,
    val date: String,
    val exercises: List<Exercise>
)
