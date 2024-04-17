package com.hudyweas.workouttogether.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.AppTheme
import com.hudyweas.workouttogether.LOGIN_SCREEN
import com.hudyweas.workouttogether.SIGN_UP_SCREEN
import com.hudyweas.workouttogether.R.string as AppText
import com.hudyweas.workouttogether.common.composable.*
import com.hudyweas.workouttogether.common.ext.basicButton
import com.hudyweas.workouttogether.common.ext.fieldModifier
import com.hudyweas.workouttogether.common.ext.smallTextButton
import com.hudyweas.workouttogether.common.ext.textButton

@Composable
fun LoginScreen(
  openAndPopUp: (String, String) -> Unit,
  viewModel: LoginViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState

  LoginScreenContent(
    uiState = uiState,
    onEmailChange = viewModel::onEmailChange,
    onPasswordChange = viewModel::onPasswordChange,
    onSignInClick = { viewModel.onSignInClick(openAndPopUp) },
    onForgotPasswordClick = viewModel::onForgotPasswordClick,
    onCreateAccountClick = {openAndPopUp(SIGN_UP_SCREEN, LOGIN_SCREEN)}
  )
}

@Composable
fun LoginScreenContent(
  modifier: Modifier = Modifier,
  uiState: LoginUiState,
  onEmailChange: (String) -> Unit,
  onPasswordChange: (String) -> Unit,
  onSignInClick: () -> Unit,
  onForgotPasswordClick: () -> Unit,
  onCreateAccountClick: () -> Unit
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .background(MaterialTheme.colorScheme.background)
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    EmailField(uiState.email, onEmailChange, Modifier.fieldModifier())

    PasswordField(uiState.password, onPasswordChange, Modifier.fieldModifier())

    BasicTextButton(text = AppText.forgot_password, modifier = Modifier.smallTextButton(), action = onForgotPasswordClick, textColor = Color.Gray)

    BasicButton(AppText.sign_in, Modifier.basicButton()) { onSignInClick() }

    TextDivider("or")

    BasicTextButton(AppText.change_to_signup, Modifier.textButton(), onCreateAccountClick, textColor = null)
  }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
  val uiState = LoginUiState(
    email = "email@test.com"
  )

  AppTheme {
    LoginScreenContent(
      uiState = uiState,
      onEmailChange = { },
      onPasswordChange = { },
      onSignInClick = { },
      onForgotPasswordClick = { },
      onCreateAccountClick = { }
    )
  }
}
