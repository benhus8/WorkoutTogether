

package com.hudyweas.workouttogether.model.service.impl

import com.hudyweas.workouttogether.model.Workout
import com.hudyweas.workouttogether.model.service.AccountService
import com.hudyweas.workouttogether.model.service.StorageService
import com.hudyweas.workouttogether.model.service.trace
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await

class StorageServiceImpl @Inject constructor(
  private val firestore: FirebaseFirestore,
  private val auth: AccountService
  ) : StorageService {

  private val collection get() = firestore.collection(WORKOUT_COLLECTION)
    .whereEqualTo(USER_ID_FIELD, auth.currentUserId)

  @OptIn(ExperimentalCoroutinesApi::class)
  override val workouts: Flow<List<Workout>>
    get() =
      auth.currentUser.flatMapLatest { user ->
        firestore
          .collection(WORKOUT_COLLECTION)
          .whereEqualTo(USER_ID_FIELD, user.id)
          .orderBy(CREATED_AT_FIELD, Query.Direction.DESCENDING)
          .dataObjects()
      }

  override suspend fun getWorkout(taskId: String): Workout? =
    firestore.collection(WORKOUT_COLLECTION).document(taskId).get().await().toObject()

  override suspend fun save(workout: Workout): String =
    trace(SAVE_TASK_TRACE) {
     val updatedTask = workout.copy(userId = auth.currentUserId)
      firestore.collection(WORKOUT_COLLECTION).add(updatedTask).await().id
    }

  override suspend fun update(workout: Workout): Unit =
    trace(UPDATE_TASK_TRACE) {
      firestore.collection(WORKOUT_COLLECTION).document(workout.id).set(workout).await()
    }

  override suspend fun delete(workoutId: String) {
    firestore.collection(WORKOUT_COLLECTION).document(workoutId).delete().await()
  }

  companion object {
    private const val USER_ID_FIELD = "userId"
    private const val CREATED_AT_FIELD = "createdAt"
    private const val WORKOUT_COLLECTION = "workouts"
    private const val SAVE_TASK_TRACE = "saveTask"
    private const val UPDATE_TASK_TRACE = "updateTask"
  }
}
