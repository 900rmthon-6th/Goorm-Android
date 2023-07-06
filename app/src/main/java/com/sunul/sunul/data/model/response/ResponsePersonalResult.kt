package com.sunul.sunul.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePersonalResult(
    @SerializedName("mbti")
    val mbti: String,
    @SerializedName("reason")
    val reason: String
)