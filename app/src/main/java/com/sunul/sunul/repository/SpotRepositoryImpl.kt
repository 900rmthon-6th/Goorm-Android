package com.sunul.sunul.repository

import com.sunul.sunul.data.model.response.ResponseMbtiChat
import com.sunul.sunul.data.model.response.ResponseSpots
import com.sunul.sunul.domain.SpotRepository
import com.sunul.sunul.source.SpotDataSource

class SpotRepositoryImpl(private val spotDataSource: SpotDataSource):SpotRepository {
    override suspend fun getSpots(mbti: String): ResponseSpots = spotDataSource.getSpots(mbti)
    override suspend fun getSpotChat(mbti: String): ResponseMbtiChat =spotDataSource.getSpotChat(mbti)
}