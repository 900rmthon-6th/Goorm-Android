package com.sunul.sunul.data.api

import com.google.gson.internal.GsonBuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sunul.sunul.BuildConfig
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiClient {

    private const val BASE_URL = BuildConfig.BASE_URL
    private var retrofit: Retrofit? = null
    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    //BASE API
    @OptIn(ExperimentalSerializationApi::class, InternalCoroutinesApi::class)
    fun getApiClient(): Retrofit? {
        kotlinx.coroutines.internal.synchronized(this) {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                    .build()
            }
            return retrofit
        }
    }
}