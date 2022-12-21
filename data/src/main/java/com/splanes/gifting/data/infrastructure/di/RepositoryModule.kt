package com.splanes.gifting.data.infrastructure.di

import com.splanes.gifting.data.feature.auth.repository.AuthRepositoryImpl
import com.splanes.gifting.domain.feature.auth.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun authRepository(impl: AuthRepositoryImpl): AuthRepository
}
