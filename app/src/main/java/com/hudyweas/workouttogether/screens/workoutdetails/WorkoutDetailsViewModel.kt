package com.hudyweas.workouttogether.screens.workoutdetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.hudyweas.workouttogether.TASK_ID
import com.hudyweas.workouttogether.common.ext.idFromParameter
import com.hudyweas.workouttogether.model.Workout
import com.hudyweas.workouttogether.model.service.LogService
import com.hudyweas.workouttogether.model.service.StorageService
import com.hudyweas.workouttogether.screens.WorkoutTogetherViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    logService: LogService,
    private val storageService: StorageService,
): WorkoutTogetherViewModel(logService){
    val workout = mutableStateOf(Workout())

    init {
        val workoutId = savedStateHandle.get<String>(TASK_ID)
        if (workoutId != null) {
            launchCatching {
                workout.value = storageService.getWorkout(workoutId.idFromParameter()) ?: Workout()
            }
        }
    }
}