package com.sunul.sunul.data.api

import com.sunul.sunul.data.model.request.RequestTest
import com.sunul.sunul.data.model.response.ResponseTest
import retrofit2.http.Body
import retrofit2.http.POST

interface TestService {
    @POST("/chat/test/spot")
    suspend fun getTest(@Body requestTest:RequestTest):ResponseTest
}