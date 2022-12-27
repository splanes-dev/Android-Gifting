package com.splanes.gifting.data.infrastructure.di

import com.splanes.gifting.data.feature.auth.repository.AuthRepositoryImpl
import com.splanes.gifting.data.feature.list.wishlist.repository.WishlistRepositoryImpl
import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun authRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun wishlistRepository(impl: WishlistRepositoryImpl): WishlistRepository
}
