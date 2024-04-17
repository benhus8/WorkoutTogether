package com.hudyweas.workouttogether.screens.workouts

import androidx.compose.runtime.mutableStateOf
import com.hudyweas.workouttogether.EDIT_WORKOUT_SCREEN
import com.hudyweas.workouttogether.TASK_ID
import com.hudyweas.workouttogether.WORKOUT_DETAILS_SCREEN
import com.hudyweas.workouttogether.model.Workout
import com.hudyweas.workouttogether.model.service.ConfigurationService
import com.hudyweas.workouttogether.model.service.LogService
import com.hudyweas.workouttogether.model.service.StorageService
import com.hudyweas.workouttogether.screens.WorkoutTogetherViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
  logService: LogService,
  private val storageService: StorageService,
  private val configurationService: ConfigurationService
) : WorkoutTogetherViewModel(logService) {
  val options = mutableStateOf<List<String>>(listOf())

  val workouts = storageService.workouts

  fun loadTaskOptions() {
    val hasEditOption = configurationService.isShowTaskEditButtonConfig
    options.value = WorkoutActionOption.getOptions(hasEditOption)
  }

  fun onAddClick(openScreen: (String) -> Unit) = openScreen(EDIT_WORKOUT_SCREEN)

  fun onWorkoutActionClick(openScreen: (String) -> Unit, workout: Workout, action: String) {
    when (WorkoutActionOption.getByTitle(action)) {
      WorkoutActionOption.A -> {}
      WorkoutActionOption.EditWorkout -> openScreen("$EDIT_WORKOUT_SCREEN?$TASK_ID={${workout.id}}")
      WorkoutActionOption.DeleteTask -> onDeleteTaskClick(workout)
    }
  }

  fun onWorkoutDetailsClick(openScreen: (String) -> Unit, workout: Workout) {
    openScreen("$WORKOUT_DETAILS_SCREEN?$TASK_ID={${workout.id}}")
  }

  private fun onDeleteTaskClick(workout: Workout) {
    launchCatching { storageService.delete(workout.id) }
  }
}
