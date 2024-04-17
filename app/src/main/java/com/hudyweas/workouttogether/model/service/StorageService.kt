package com.hudyweas.workouttogether.model.service

import com.hudyweas.workouttogether.model.Workout
import kotlinx.coroutines.flow.Flow

interface StorageService {
  val workouts: Flow<List<Workout>>
  suspend fun getWorkout(taskId: String): Workout?
  suspend fun save(workout: Workout): String
  suspend fun update(workout: Workout)
  suspend fun delete(workoutId: String)
}
