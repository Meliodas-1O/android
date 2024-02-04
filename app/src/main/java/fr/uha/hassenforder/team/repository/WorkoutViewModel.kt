package fr.uha.hassenforder.team.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uha.hassenforder.team.model.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
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
    }