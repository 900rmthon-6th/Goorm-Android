package com.sunul.sunul.source

import com.sunul.sunul.data.api.OnBoardingService
import com.sunul.sunul.data.model.request.RequestPersonalResult

class OnBoardingDataSource(private val onBoardingService: OnBoardingService) {
    suspend fun getQnA() = onBoardingService.getQnA()
    suspend fun postQnA(requestPersonalResult: RequestPersonalResult) =
        onBoardingService.postQnA(requestPersonalResult)
}