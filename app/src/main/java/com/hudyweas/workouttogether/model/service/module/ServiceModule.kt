

package com.hudyweas.workouttogether.model.service.module

import com.hudyweas.workouttogether.model.service.AccountService
import com.hudyweas.workouttogether.model.service.ConfigurationService
import com.hudyweas.workouttogether.model.service.LogService
import com.hudyweas.workouttogether.model.service.StorageService
import com.hudyweas.workouttogether.model.service.impl.AccountServiceImpl
import com.hudyweas.workouttogether.model.service.impl.ConfigurationServiceImpl
import com.hudyweas.workouttogether.model.service.impl.LogServiceImpl
import com.hudyweas.workouttogether.model.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
  @Binds abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

  @Binds abstract fun provideLogService(impl: LogServiceImpl): LogService

  @Binds abstract fun provideStorageService(impl: StorageServiceImpl): StorageService

  @Binds abstract fun provideConfigurationService(impl: ConfigurationServiceImpl): ConfigurationService
}
