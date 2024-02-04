package fr.uha.hassenforder.team.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(private val exerciseRepository: ExerciseRepository) : ViewModel() {
    private val _exercises: MutableStateFlow<List<Exercise>> = MutableStateFlow(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises

    init {
        getAllExercises()
    }
    private fun getAllExercises() {
        viewModelScope.launch {
            exerciseRepository.getAll()
                .collect { exercises ->
                    _exercises.value = exercises
                }
        }
    }
    fun insertExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.insert(exercise)
        }
    }
    fun getExercisesByType(type: ExerciseType): Flow<List<Exercise>> {
        return exerciseRepository.getExercisesByType(type)
    }
    fun getExercisesForWorkout(workoutId: Long): Flow<List<Exercise>> {
        return exerciseRepository.getExercisesForWorkout(workoutId)
    }
    fun getExercisesStartingWith(pattern: String): Flow<List<Exercise>> {
        return exerciseRepository.getExercisesStartingWith(pattern)
    }
}
