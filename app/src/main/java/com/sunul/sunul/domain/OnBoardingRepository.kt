package com.sunul.sunul.domain

import com.sunul.sunul.data.model.response.ResponseQnA

interface OnBoardingRepository {
    suspend fun getQnA(qid:String): ResponseQnA
}