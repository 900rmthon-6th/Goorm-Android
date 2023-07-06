package com.sunul.sunul.data.api

import com.sunul.sunul.data.model.request.RequestPersonalResult
import com.sunul.sunul.data.model.response.ResponsePersonalResult
import com.sunul.sunul.data.model.response.ResponseQnA
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OnBoardingService {
    @GET("/mbti/all")
    suspend fun getQnA(): ResponseQnA

    @POST("/user/mbti")
    suspend fun postQnA(@Body requestPersonalResult: RequestPersonalResult): ResponsePersonalResult
}