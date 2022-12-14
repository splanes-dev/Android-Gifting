package com.splanes.gifting.data.infrastructure.di

import com.splanes.gifting.data.feature.user.repository.UserRepositoryImpl
import com.splanes.gifting.domain.feature.user.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun userRepository(impl: UserRepositoryImpl): UserRepository
}
