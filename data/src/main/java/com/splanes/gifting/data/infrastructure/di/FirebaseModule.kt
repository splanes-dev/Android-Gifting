package com.splanes.gifting.data.infrastructure.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun firebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun firebaseDatabase(): FirebaseDatabase =
        FirebaseDatabase.getInstance(
            "https://gifting-caf5f-default-rtdb.europe-west1.firebasedatabase.app/"
        )
}
