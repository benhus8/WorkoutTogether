

package com.hudyweas.workouttogether.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hudyweas.workouttogether.common.snackbar.SnackbarManager
import com.hudyweas.workouttogether.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.hudyweas.workouttogether.model.service.LogService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class WorkoutTogetherViewModel(private val logService: LogService) : ViewModel() {
  fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
    viewModelScope.launch(
      CoroutineExceptionHandler { _, throwable ->
        if (snackbar) {
          SnackbarManager.showMessage(throwable.toSnackbarMessage())
        }
        logService.logNonFatalCrash(throwable)
      },
      block = block
    )
}
