package com.sunul.sunul.source

import com.sunul.sunul.data.api.OnBoardingService

class OnBoardingDataSource(private val onBoardingService: OnBoardingService) {
    suspend fun getQnA() = onBoardingService.getQnA()
}