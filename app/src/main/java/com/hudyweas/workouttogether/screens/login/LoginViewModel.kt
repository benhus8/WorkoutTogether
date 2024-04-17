

package com.hudyweas.workouttogether.screens.login

import androidx.compose.runtime.mutableStateOf
import com.hudyweas.workouttogether.LOGIN_SCREEN
import com.hudyweas.workouttogether.R.string as AppText
import com.hudyweas.workouttogether.SETTINGS_SCREEN
import com.hudyweas.workouttogether.common.ext.isValidEmail
import com.hudyweas.workouttogether.common.snackbar.SnackbarManager
import com.hudyweas.workouttogether.model.service.AccountService
import com.hudyweas.workouttogether.model.service.LogService
import com.hudyweas.workouttogether.screens.WorkoutTogetherViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val accountService: AccountService,
  logService: LogService
) : WorkoutTogetherViewModel(logService) {
  var uiState = mutableStateOf(LoginUiState())
    private set

  private val email
    get() = uiState.value.email
  private val password
    get() = uiState.value.password

  fun onEmailChange(newValue: String) {
    uiState.value = uiState.value.copy(email = newValue)
  }

  fun onPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(password = newValue)
  }

  fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
    if (!email.isValidEmail()) {
      SnackbarManager.showMessage(AppText.email_error)
      return
    }

    if (password.isBlank()) {
      SnackbarManager.showMessage(AppText.empty_password_error)
      return
    }

    launchCatching {
      accountService.authenticate(email, password)
      openAndPopUp(SETTINGS_SCREEN, LOGIN_SCREEN)
    }
  }

  fun onForgotPasswordClick() {
    if (!email.isValidEmail()) {
      SnackbarManager.showMessage(AppText.email_error)
      return
    }

    launchCatching {
      accountService.sendRecoveryEmail(email)
      SnackbarManager.showMessage(AppText.recovery_email_sent)
    }
  }
}
