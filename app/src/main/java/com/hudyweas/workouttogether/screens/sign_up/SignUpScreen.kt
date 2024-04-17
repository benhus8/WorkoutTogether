

package com.hudyweas.workouttogether.screens.sign_up

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.AppTheme
import com.hudyweas.workouttogether.LOGIN_SCREEN
import com.hudyweas.workouttogether.SIGN_UP_SCREEN
import com.hudyweas.workouttogether.R.drawable as AppIcon
import com.hudyweas.workouttogether.R.string as AppText
import com.hudyweas.workouttogether.common.composable.*
import com.hudyweas.workouttogether.common.ext.basicButton
import com.hudyweas.workouttogether.common.ext.fieldModifier
import com.hudyweas.workouttogether.common.ext.textButton

@Composable
fun SignUpScreen(
  openAndPopUp: (String, String) -> Unit,
  viewModel: SignUpViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState

  SignUpScreenContent(
    uiState = uiState,
    onEmailChange = viewModel::onEmailChange,
    onPasswordChange = viewModel::onPasswordChange,
    onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
    onSignUpClick = { viewModel.onSignUpClick(openAndPopUp) },
    onNicknameChange = viewModel::onNicknameChange,
    onLoginClick = { openAndPopUp(LOGIN_SCREEN, SIGN_UP_SCREEN) }
  )
}

@Composable
fun SignUpScreenContent(
  modifier: Modifier = Modifier,
  uiState: SignUpUiState,
  onEmailChange: (String) -> Unit,
  onPasswordChange: (String) -> Unit,
  onNicknameChange: (String) -> Unit,
  onRepeatPasswordChange: (String) -> Unit,
  onSignUpClick: () -> Unit,
  onLoginClick: () -> Unit
) {
  val fieldModifier = Modifier.fieldModifier()

  Column(
    modifier = modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .background(MaterialTheme.colorScheme.background)
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    EmailField(uiState.email, onEmailChange, fieldModifier)
    BasicField(AppText.nickname, uiState.nickname, onNicknameChange, fieldModifier, AppIcon.ic_sign_in)
    PasswordField(uiState.password, onPasswordChange, fieldModifier)
    RepeatPasswordField(uiState.repeatPassword, onRepeatPasswordChange, fieldModifier)

    BasicButton(AppText.create_account, Modifier.basicButton()) {
      onSignUpClick()
    }

    TextDivider("or")

    BasicTextButton(AppText.change_to_login, Modifier.textButton(), onLoginClick, textColor = null)

  }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
  val uiState = SignUpUiState(
    email = "email@test.com"
  )

  AppTheme {
    SignUpScreenContent(
      uiState = uiState,
      onEmailChange = { },
      onPasswordChange = { },
      onRepeatPasswordChange = { },
      onNicknameChange = { },
      onSignUpClick = { },
      onLoginClick = { }
    )
  }
}
