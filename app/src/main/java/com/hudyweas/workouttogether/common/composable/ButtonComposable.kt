

package com.hudyweas.workouttogether.common.composable

import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun BasicTextButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit, textColor : Color?) {
  TextButton(onClick = action, modifier = modifier) {
    Text(
      text = stringResource(text),
      color = textColor ?: MaterialTheme.colorScheme.primary
    )
  }
}

@Composable
fun BasicButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
  Button(
    onClick = action,
    modifier = modifier,
    colors =
      ButtonDefaults.buttonColors()
  ) {
    Text(text = stringResource(text), fontSize = 16.sp)
  }
}

@Composable
fun DialogConfirmButton(@StringRes text: Int, action: () -> Unit) {
  Button(
    onClick = action,
    colors =
      ButtonDefaults.buttonColors()
  ) {
    Text(text = stringResource(text))
  }
}

@Composable
fun DialogCancelButton(@StringRes text: Int, action: () -> Unit) {
  Button(
    onClick = action,
    colors =
      ButtonDefaults.buttonColors()
  ) {
    Text(text = stringResource(text))
  }
}
