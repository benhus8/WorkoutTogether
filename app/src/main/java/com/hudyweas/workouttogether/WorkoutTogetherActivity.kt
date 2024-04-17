package com.hudyweas.workouttogether

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import dagger.hilt.android.AndroidEntryPoint

// WorkoutTogetherActivity starts the first composable, which uses material cards that are still experimental.
// TODO: Update material dependency and experimental annotations once the API stabilizes.
@AndroidEntryPoint
@ExperimentalMaterialApi
class WorkoutTogetherActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent { WorkoutTogetherApp() }
  }
}
