package fr.uha.hassenforder.team.repository

import androidx.lifecycle.viewModelScope
import fr.uha.hassenforder.team.database.ExerciseWorkoutAssociationDAO
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.ExerciseWorkoutAssociation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ExerciseWorkoutAssociationRepository (
    private val associationDAO: ExerciseWorkoutAssociationDAO)
{
    val allAssociations: Flow<List<ExerciseWorkoutAssociation>> = associationDAO.getAllAssociations()

    fun getAllAssociation():Flow<List<ExerciseWorkoutAssociation>> {
        return associationDAO.getAllAssociations()
    }

    suspend fun insertAssociation(association: ExerciseWorkoutAssociation) {
        withContext(Dispatchers.IO) {
            associationDAO.insert(association)
        }
    }

    suspend fun deleteAssociation(association: ExerciseWorkoutAssociation) {
        withContext(Dispatchers.IO) {
            associationDAO.delete(association)
        }
    }

    suspend fun insertAllAssociations(associations: List<ExerciseWorkoutAssociation>) {
        withContext(Dispatchers.IO) {
            associationDAO.insertAll(associations)
        }
    }

    suspend fun deleteAllAssociations() {
        withContext(Dispatchers.IO) {
            associationDAO.deleteAllAssociations()
        }
    }

}
