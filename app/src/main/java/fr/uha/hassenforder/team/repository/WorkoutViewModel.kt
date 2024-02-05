package fr.uha.hassenforder.team.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uha.hassenforder.team.model.Exercise
import fr.uha.hassenforder.team.model.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor
    (private val  workoutRepository: WorkoutRepository):ViewModel()
    {
        private val _workouts: MutableStateFlow<List<Workout>> = MutableStateFlow(emptyList())
        val workouts: StateFlow<List<Workout>> = _workouts

        init {
            getAllWorkouts()
        }
        private fun getAllWorkouts() {
            viewModelScope.launch {
                workoutRepository.getAll()
                    .collect { workouts ->
                        _workouts.value = workouts
                    }
            }
        }

        fun insertWorkout(workout: Workout) {
            viewModelScope.launch(Dispatchers.IO) {
                workoutRepository.insertWorkout(workout)
            }
        }
        fun getLatestWorkout(): Workout? {
            var latestWorkout: Workout? = null
            viewModelScope.launch {
                latestWorkout = workoutRepository.getLatestWorkout()
            }
            return latestWorkout
        }

        fun updateWorkout(workout: Workout) {
            viewModelScope.launch(Dispatchers.IO) {
                workoutRepository.updateWorkout(workout)
            }
        }
        fun deleteWorkout(workout: Workout){
            viewModelScope.launch(Dispatchers.IO) {
                workoutRepository.deleteWorkout(workout)
            }
        }
        fun getWorkoutById(id: Long): Flow<Workout> {
            return workoutRepository.getWorkoutById(id)
        }

        fun getLastWeekWorkouts(oneWeekAgo: Date):Flow<List<Workout>>{
            return workoutRepository.getLastWeekWorkout(oneWeekAgo);
        }

        fun getFirstWorkoutForToday() : Flow<Workout?> {
            return workoutRepository.getFirstWorkoutForToday();
        }

        fun getHistory(): Flow<List<Workout>> {
            return workoutRepository.getHistory();
        }
    }