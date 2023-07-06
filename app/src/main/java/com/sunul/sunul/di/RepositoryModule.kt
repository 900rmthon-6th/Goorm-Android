package com.sunul.sunul.di

import com.sunul.sunul.data.api.ApiClient
import com.sunul.sunul.data.api.TestService
import com.sunul.sunul.data.repository.TestRepositoryImpl
import com.sunul.sunul.data.source.remote.TestDataSource
import com.sunul.sunul.domain.TestRepository
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
    fun provideTestRepository(): TestRepository {
        return TestRepositoryImpl(
            TestDataSource(
                ApiClient.getApiClient()!!.create(TestService::class.java)
            )
        )
    }
}