package com.hudyweas.workouttogether.model.service

interface ConfigurationService {
  suspend fun fetchConfiguration(): Boolean
  val isShowTaskEditButtonConfig: Boolean
}
