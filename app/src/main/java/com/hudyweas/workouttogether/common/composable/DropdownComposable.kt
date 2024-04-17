package com.hudyweas.workouttogether.common.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hudyweas.workouttogether.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownContextMenu(
  options: List<String>,
  modifier: Modifier,
  onActionClick: (String) -> Unit
) {
  var isExpanded by remember { mutableStateOf(false) }

  ExposedDropdownMenuBox(
    expanded = isExpanded,
    modifier = modifier,
    onExpandedChange = { isExpanded = !isExpanded }
  ) {

    Icon(
      painterResource(id = R.drawable.ic_menu),
      "More",
      modifier = Modifier.padding(8.dp, 0.dp).menuAnchor()
    )

    ExposedDropdownMenu(
      modifier = Modifier.width(180.dp),
      expanded = isExpanded,
      onDismissRequest = { isExpanded = false }
    ) {
      options.forEach { selectionOption ->
        DropdownMenuItem(
          text = {
            Text(text = selectionOption)
          },
          onClick = {
            isExpanded = false
            onActionClick(selectionOption)
          }
        )
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownIconSelector(
  @DrawableRes options: List<Int>,
  @DrawableRes selection: Int,
  modifier: Modifier,
  onNewValue: (Int) -> Unit
){
  var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        modifier = modifier,
        onExpandedChange = { isExpanded = !isExpanded }
    ) {
        Card(
          modifier = modifier.menuAnchor(),
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
          ) {
            Column(modifier = Modifier.weight(1f)) { Text("Icon") }

            Icon(painter = painterResource(selection), contentDescription = "Icon")
          }
        }

        ExposedDropdownMenu(
        modifier = Modifier.width(180.dp),
        expanded = isExpanded,
        onDismissRequest = { isExpanded = false }
        ) {
        options.forEach { selectionOption ->
            DropdownMenuItem(
            text = {
                Icon(
                painterResource(id = selectionOption),
                "Icon",
                modifier = Modifier.padding(8.dp, 0.dp)
                )
            },
            onClick = {
                onNewValue(selectionOption)
                isExpanded = false
            }
            )
        }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSelector(
  @StringRes label: Int,
  options: List<String>,
  selection: String,
  modifier: Modifier,
  onNewValue: (String) -> Unit
) {
  var isExpanded by remember { mutableStateOf(false) }

  ExposedDropdownMenuBox(
    expanded = isExpanded,
    modifier = modifier,
    onExpandedChange = { isExpanded = !isExpanded }
  ) {
    TextField(
      modifier = Modifier.fillMaxWidth().menuAnchor(),
      readOnly = true,
      value = selection,
      onValueChange = {},
      label = { Text(stringResource(label)) },
      trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(isExpanded) },
    )

    ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
      options.forEach { selectionOption ->
        DropdownMenuItem(
          text = {
            Text(text = selectionOption)
          },
          onClick = {
            onNewValue(selectionOption)
            isExpanded = false
          }
        )
      }
    }
  }
}

