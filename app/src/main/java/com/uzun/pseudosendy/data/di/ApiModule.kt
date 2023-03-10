package com.uzun.pseudosendy.data.di

import com.uzun.pseudosendy.data.remote.MapsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideMeetingApi(
        retrofit: Retrofit,
    ): MapsApi = retrofit.create(MapsApi::class.java)

}