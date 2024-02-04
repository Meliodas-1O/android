package fr.uha.hassenforder.team.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseWorkoutAssociation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class ExerciseWorkoutAssociationViewModel @Inject constructor(private val associationRepository: ExerciseWorkoutAssociationRepository) : ViewModel() {
    private val _associations: MutableStateFlow<List<ExerciseWorkoutAssociation>> = MutableStateFlow(emptyList())
    val associations: StateFlow<List<ExerciseWorkoutAssociation>> = _associations

    init {
        getAllAssociations()
    }
    private fun getAllAssociations() {
        viewModelScope.launch {
            associationRepository.getAllAssociation()
                .collect { association ->
                    _associations.value = association
                }
        }
    }
    fun insertAssociation(association: ExerciseWorkoutAssociation) {
        viewModelScope.launch(Dispatchers.IO) {
            associationRepository.insertAssociation(association)
        }
    }

    fun deleteAssociation(association: ExerciseWorkoutAssociation) {
        viewModelScope.launch(Dispatchers.IO) {
            associationRepository.deleteAssociation(association)
        }
    }

    fun insertAllAssociations(associations: List<ExerciseWorkoutAssociation>) {
        viewModelScope.launch(Dispatchers.IO) {
            associationRepository.insertAllAssociations(associations)
        }
    }

    fun deleteAllAssociations() {
        viewModelScope.launch(Dispatchers.IO) {
            associationRepository.deleteAllAssociations()
        }
    }

}