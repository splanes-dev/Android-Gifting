package com.splanes.gifting.data.infrastructure.di

import com.splanes.gifting.data.feature.auth.datasource.AuthLocalDataSource
import com.splanes.gifting.data.feature.auth.datasource.AuthRemoteDataSource
import com.splanes.gifting.data.feature.auth.datasource.impl.AuthLocalDataSourceImpl
import com.splanes.gifting.data.feature.auth.datasource.impl.AuthRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun authRemoteDataSource(impl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    abstract fun authLocalDataSource(impl: AuthLocalDataSourceImpl): AuthLocalDataSource
}
