

package com.hudyweas.workouttogether.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import com.hudyweas.workouttogether.R.string as AppText
import com.hudyweas.workouttogether.SIGN_UP_SCREEN
import com.hudyweas.workouttogether.WORKOUTS_SCREEN
import com.hudyweas.workouttogether.common.ext.isValidEmail
import com.hudyweas.workouttogether.common.ext.isValidNickname
import com.hudyweas.workouttogether.common.ext.isValidPassword
import com.hudyweas.workouttogether.common.ext.passwordMatches
import com.hudyweas.workouttogether.common.snackbar.SnackbarManager
import com.hudyweas.workouttogether.model.service.AccountService
import com.hudyweas.workouttogether.model.service.LogService
import com.hudyweas.workouttogether.screens.WorkoutTogetherViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
  private val accountService: AccountService,
  logService: LogService
) : WorkoutTogetherViewModel(logService) {
  var uiState = mutableStateOf(SignUpUiState())
    private set

  private val email
    get() = uiState.value.email
  private val password
    get() = uiState.value.password
  private val nickname
    get() = uiState.value.nickname

  fun onEmailChange(newValue: String) {
    uiState.value = uiState.value.copy(email = newValue)
  }

  fun onNicknameChange(newValue: String) {
    uiState.value = uiState.value.copy(nickname = newValue)
  }

  fun onPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(password = newValue)
  }

  fun onRepeatPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(repeatPassword = newValue)
  }

  fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
    if (!email.isValidEmail()) {
      SnackbarManager.showMessage(AppText.email_error)
      return
    }

    if (!password.isValidPassword()) {
      SnackbarManager.showMessage(AppText.password_error)
      return
    }

    if (!nickname.isValidNickname()) {
      SnackbarManager.showMessage(AppText.nickname_error)
      return
    }

    if (!password.passwordMatches(uiState.value.repeatPassword)) {
      SnackbarManager.showMessage(AppText.password_match_error)
      return
    }

    launchCatching {
      accountService.linkAccount(email, password)
      accountService.updateProfile(nickname = nickname, photoUrl = null)

      openAndPopUp(WORKOUTS_SCREEN, SIGN_UP_SCREEN)
    }
  }
}
