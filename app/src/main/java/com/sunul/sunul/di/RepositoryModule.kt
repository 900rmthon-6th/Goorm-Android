package com.sunul.sunul.di

import com.sunul.sunul.data.api.ApiClient
import com.sunul.sunul.data.api.OnBoardingService
import com.sunul.sunul.data.api.SpotService
import com.sunul.sunul.domain.OnBoardingRepository
import com.sunul.sunul.domain.SpotRepository
import com.sunul.sunul.repository.OnBoardingRepositoryImpl
import com.sunul.sunul.repository.SpotRepositoryImpl
import com.sunul.sunul.source.OnBoardingDataSource
import com.sunul.sunul.source.SpotDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideOnBoardingRepository(): OnBoardingRepository {
        return OnBoardingRepositoryImpl(
            OnBoardingDataSource(
                ApiClient.getApiClient()!!.create(OnBoardingService::class.java)
            )
        )
    }

    @Singleton
    @Provides
    fun provideSpotRepository(): SpotRepository {
        return SpotRepositoryImpl(
            SpotDataSource(
                ApiClient.getApiClient()!!.create(SpotService::class.java)
            )
        )
    }
}