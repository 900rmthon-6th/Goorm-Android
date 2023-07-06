package com.sunul.sunul.di

import com.sunul.sunul.data.api.ApiClient
import com.sunul.sunul.data.api.OnBoardingService
import com.sunul.sunul.domain.OnBoardingRepository
import com.sunul.sunul.repository.OnBoardingRepositoryImpl
import com.sunul.sunul.source.OnBoardingDataSource
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
}