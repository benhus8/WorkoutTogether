

package com.hudyweas.workouttogether.model.service.impl

import com.hudyweas.workouttogether.BuildConfig
import com.hudyweas.workouttogether.R.xml as AppConfig
import com.hudyweas.workouttogether.model.service.ConfigurationService
import com.hudyweas.workouttogether.model.service.trace
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.get
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class ConfigurationServiceImpl @Inject constructor() : ConfigurationService {
  private val remoteConfig
    get() = Firebase.remoteConfig

  init {
    if (BuildConfig.DEBUG) {
      val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 0 }
      remoteConfig.setConfigSettingsAsync(configSettings)
    }

    remoteConfig.setDefaultsAsync(AppConfig.remote_config_defaults)
  }

  override suspend fun fetchConfiguration(): Boolean =
    trace(FETCH_CONFIG_TRACE) { remoteConfig.fetchAndActivate().await() }

  override val isShowTaskEditButtonConfig: Boolean
    get() = remoteConfig[SHOW_WORKOUT_EDIT_BUTTON_KEY].asBoolean()

  companion object {
    private const val SHOW_WORKOUT_EDIT_BUTTON_KEY = "show_workout_edit_button"
    private const val FETCH_CONFIG_TRACE = "fetchConfig"
  }
}
