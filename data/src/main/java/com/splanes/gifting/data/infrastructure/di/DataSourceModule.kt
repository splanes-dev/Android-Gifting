package com.splanes.gifting.data.infrastructure.di

import com.splanes.gifting.data.feature.auth.datasource.AuthLocalDataSource
import com.splanes.gifting.data.feature.auth.datasource.AuthRemoteDataSource
import com.splanes.gifting.data.feature.auth.datasource.impl.AuthLocalDataSourceImpl
import com.splanes.gifting.data.feature.auth.datasource.impl.AuthRemoteDataSourceImpl
import com.splanes.gifting.data.feature.list.wishlist.datasource.WishlistRemoteDataSource
import com.splanes.gifting.data.feature.list.wishlist.datasource.impl.WishlistRemoteDataSourceImpl
import com.splanes.gifting.data.feature.profile.datasource.ProfileRemoteDataSource
import com.splanes.gifting.data.feature.profile.datasource.impl.ProfileRemoteDataSourceImpl
import com.splanes.gifting.data.feature.tag.datasource.TagRemoteDataSource
import com.splanes.gifting.data.feature.tag.datasource.impl.TagRemoteDataSourceImpl
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

    @Binds
    abstract fun wishlistRemoteDataSource(
        impl: WishlistRemoteDataSourceImpl
    ): WishlistRemoteDataSource

    @Binds
    abstract fun tagRemoteDataSource(impl: TagRemoteDataSourceImpl): TagRemoteDataSource

    @Binds
    abstract fun profileRemoteDataSource(impl: ProfileRemoteDataSourceImpl): ProfileRemoteDataSource
}
