package com.sunul.sunul.domain

import com.sunul.sunul.data.model.request.RequestPersonalResult
import com.sunul.sunul.data.model.response.ResponsePersonalResult
import com.sunul.sunul.data.model.response.ResponseQnA

interface OnBoardingRepository {
    suspend fun getQnA(): ResponseQnA
    suspend fun postQnA(requestPersonalResult: RequestPersonalResult): ResponsePersonalResult
}