package com.sunul.sunul.domain

import com.sunul.sunul.data.model.response.ResponseMbtiChat
import com.sunul.sunul.data.model.response.ResponseSpots

interface SpotRepository {
    suspend fun getSpots(mbti:String):ResponseSpots

    suspend fun getSpotChat(mbti:String):ResponseMbtiChat
}