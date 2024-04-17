package com.hudyweas.workouttogether.screens.workouts

enum class WorkoutActionOption(val title: String) {
  EditWorkout("Edit workout"),
  A("A"),
  DeleteTask("Delete workout");

  companion object {
    fun getByTitle(title: String): WorkoutActionOption {
      values().forEach { action -> if (title == action.title) return action }

      return EditWorkout
    }

    fun getOptions(hasEditOption: Boolean): List<String> {
      val options = mutableListOf<String>()
      values().forEach { workoutAction ->
        if (hasEditOption || workoutAction != A) {
          options.add(workoutAction.title)
        }
      }
      return options
    }
  }
}
