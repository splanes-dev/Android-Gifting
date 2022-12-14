package com.splanes.gifting.data.infrastructure.di

import com.splanes.gifting.data.feature.user.datasource.UserLocalDataSource
import com.splanes.gifting.data.feature.user.datasource.UserLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun userLocalDataSource(impl: UserLocalDataSourceImpl): UserLocalDataSource
}
