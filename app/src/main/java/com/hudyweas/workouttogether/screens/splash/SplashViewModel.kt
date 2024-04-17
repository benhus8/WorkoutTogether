

package com.hudyweas.workouttogether.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.hudyweas.workouttogether.SPLASH_SCREEN
import com.hudyweas.workouttogether.WORKOUTS_SCREEN
import com.hudyweas.workouttogether.model.service.AccountService
import com.hudyweas.workouttogether.model.service.ConfigurationService
import com.hudyweas.workouttogether.model.service.LogService
import com.hudyweas.workouttogether.screens.WorkoutTogetherViewModel
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  configurationService: ConfigurationService,
  private val accountService: AccountService,
  logService: LogService
) : WorkoutTogetherViewModel(logService) {
  val showError = mutableStateOf(false)

  init {
    launchCatching { configurationService.fetchConfiguration() }
  }

  fun onAppStart(openAndPopUp: (String, String) -> Unit) {

    showError.value = false
    if (accountService.hasUser) openAndPopUp(WORKOUTS_SCREEN, SPLASH_SCREEN)
    else createAnonymousAccount(openAndPopUp)
  }

  private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
    launchCatching(snackbar = false) {
      try {
        accountService.createAnonymousAccount()
      } catch (ex: FirebaseAuthException) {
        showError.value = true
        throw ex
      }
      openAndPopUp(WORKOUTS_SCREEN, SPLASH_SCREEN)
    }
  }
}
