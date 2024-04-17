

package com.hudyweas.workouttogether.screens.workouts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AppTheme
import com.hudyweas.workouttogether.R
import com.hudyweas.workouttogether.common.static.navigationItemsList
import com.hudyweas.workouttogether.model.Workout
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction2

@Composable
//@ExperimentalMaterialApi
fun WorkoutsScreen(
  openScreen: (String) -> Unit,
  viewModel: WorkoutViewModel = hiltViewModel()
) {
  val workouts = viewModel.workouts.collectAsStateWithLifecycle(emptyList())
  val options by viewModel.options

  WorkoutsScreenContent(
    workouts = workouts.value,
    options = options,
    onAddClick = viewModel::onAddClick,
    onTaskActionClick = viewModel::onWorkoutActionClick,
    onWorkoutDetailsClick = viewModel::onWorkoutDetailsClick,
    openScreen = openScreen
  )

  LaunchedEffect(viewModel) { viewModel.loadTaskOptions() }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
//@ExperimentalMaterialApi
fun WorkoutsScreenContent(
  modifier: Modifier = Modifier,
  workouts: List<Workout>,
  options: List<String>,
  onAddClick: ((String) -> Unit) -> Unit,
  onTaskActionClick: ((String) -> Unit, Workout, String) -> Unit,
  openScreen: (String) -> Unit,
  onWorkoutDetailsClick: KFunction2<(String) -> Unit, Workout, Unit>
) {

  Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background
  ) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
      mutableStateOf(0)
    }
    ModalNavigationDrawer(
      drawerContent = {
        ModalDrawerSheet {
          Spacer(modifier = Modifier.height(16.dp))
          navigationItemsList.forEachIndexed { index, item ->
            NavigationDrawerItem(
              label = {
                Text(text = item.title)
              },
              selected = index == selectedItemIndex,
              onClick = {
                openScreen(item.itemId)
                selectedItemIndex = index
                scope.launch {
                  drawerState.close()
                }
              },
              icon = {
                Icon(
                  painterResource(item.icon),
                  item.description
                )
              },
              modifier = Modifier
                .padding(NavigationDrawerItemDefaults.ItemPadding)
            )
          }
        }
      },
      drawerState = drawerState
    ) {
      Scaffold(
        floatingActionButton = {
          FloatingActionButton(
            onClick = { onAddClick(openScreen) },
            content = {
              Icon(
                painterResource(R.drawable.ic_add),
                contentDescription = "Add"
              )
            }
          )
        },
        topBar = {
          TopAppBar(
            title = {
              Text(text = "Hello")
            },
            navigationIcon = {
              IconButton(onClick = {
                scope.launch {
                  drawerState.open()
                }
              }) {
                Icon(
                  painterResource(R.drawable.ic_menu),
                  "Menu",
                  tint = MaterialTheme.colorScheme.primary,

                )
              }
            },

          )
        }
      ) { paddingValues ->
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
          HorizontalDivider(
            modifier = Modifier.padding(horizontal = 32.dp),
            thickness = 1.dp
          )
          Spacer(modifier = Modifier.height(16.dp))

          LazyColumn {
            items(workouts, key = { it.id }) {workoutItem ->
              WorkoutItem(
                workout = workoutItem,
                options = options,
                onActionClick = { action -> onTaskActionClick(openScreen, workoutItem, action) },
                onWorkoutDetailsClick = { onWorkoutDetailsClick(openScreen, workoutItem) }
              )
            }
          }
        }
      }
    }
  }
}

//@Preview
//@Composable
//fun WorkoutsScreenPreview() {
//  val workout = Workout(
//    title = "Workout tittle",
//    description = "Workout description",
//  )
//
//  val options = WorkoutActionOption.getOptions(hasEditOption = true)
//
//  AppTheme {
//    WorkoutsScreenContent(
//      workouts = listOf(workout),
//      options = options,
//      onAddClick = { },
//      onTaskActionClick = { _, _, _ -> },
//      openScreen = { },
//      )
//  }
//}
