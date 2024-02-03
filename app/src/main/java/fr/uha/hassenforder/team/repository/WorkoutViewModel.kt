package fr.uha.hassenforder.team.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uha.hassenforder.team.model.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor
    (private val  workoutRepository: WorkoutRepository):ViewModel()
    {
        private val _workouts = MutableLiveData<List<Workout>>()
        val workouts: LiveData<List<Workout>>
            get() = _workouts

        fun getAllWorkouts() {
            viewModelScope.launch {
                workoutRepository.workouts.collect { workouts ->
                    _workouts.value = workouts
                }
            }
        }

        fun insertWorkout(workout: Workout) {
            viewModelScope.launch(Dispatchers.IO) {
                workoutRepository.insertWorkout(workout)
            }
        }
    }