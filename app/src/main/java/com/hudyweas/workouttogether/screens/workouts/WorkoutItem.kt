

package com.hudyweas.workouttogether.screens.workouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hudyweas.workouttogether.common.composable.DropdownContextMenu
import com.hudyweas.workouttogether.common.ext.contextMenu
import com.hudyweas.workouttogether.model.Workout

@Composable
fun WorkoutItem(
  workout: Workout,
  options: List<String>,
  onActionClick: (String) -> Unit,
  onWorkoutDetailsClick: (Workout) -> Unit
) {

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp, 0.dp, 8.dp, 8.dp),
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxWidth().clickable { onWorkoutDetailsClick(workout) },
    ) {

      Icon(painter = painterResource(id = workout.icon),
        contentDescription = workout.description,
        Modifier.height(64.dp).width(64.dp).padding(8.dp)
      )

      Column(
          modifier = Modifier
              .weight(1f)
              .clickable { onWorkoutDetailsClick(workout) }
      ) {
//        Text(SimpleDateFormat("dd/MM/yyyy, hh:mm").format(workout.workoutDate))
        Text(workout.date +", " + workout.time)
        Text(workout.title)
      }

      DropdownContextMenu(options, Modifier.contextMenu(), onActionClick)
    }
  }
}
