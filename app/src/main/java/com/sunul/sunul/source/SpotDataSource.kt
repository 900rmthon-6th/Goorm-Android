package com.sunul.sunul.source

import com.sunul.sunul.data.api.SpotService

class SpotDataSource(private val spotService: SpotService) {
    suspend fun getSpots(mbti: String) =
        spotService.getSpots(mbti)

    suspend fun getSpotChat(mbti:String)=spotService.getSpotChat(mbti)
}