package com.sunul.sunul.repository

import com.sunul.sunul.data.model.response.ResponseQnA
import com.sunul.sunul.domain.OnBoardingRepository
import com.sunul.sunul.source.OnBoardingDataSource

class OnBoardingRepositoryImpl(private val onBoardingDataSource: OnBoardingDataSource) :
    OnBoardingRepository {
    override suspend fun getQnA(qid:String): ResponseQnA = onBoardingDataSource.getQnA(qid)
}