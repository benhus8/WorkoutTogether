

package com.hudyweas.workouttogether.common.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicToolbar(@StringRes title: Int) {
  TopAppBar(
    title = { Text(stringResource(title)) },
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionToolbar(
  modifier: Modifier,
  @StringRes title: Int,
  @DrawableRes actionIcon: Int? = null,
  @DrawableRes navigationIcon: Int? = null,
  action: (() -> Unit)? = null,
  navigationAction: (() -> Unit)? = null
) {
  TopAppBar(
    title = { Text(text = stringResource(title)) },

    navigationIcon = navigationAction.let {
      {
        if (navigationAction != null && navigationIcon != null) {
          IconButton(onClick = navigationAction) {
            Icon(painter = painterResource(navigationIcon), contentDescription = "Navigation Icon", tint = MaterialTheme.colorScheme.primary)
            }
        }
      }
    },
    actions = {
      if (action != null && actionIcon != null) {
        IconButton(onClick = action) {
          Icon(painter = painterResource(actionIcon), contentDescription = "Primary Icon", tint = MaterialTheme.colorScheme.primary)
        }
      }
    }
  )
}
