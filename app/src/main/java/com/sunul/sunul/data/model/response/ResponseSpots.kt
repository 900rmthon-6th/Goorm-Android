package com.sunul.sunul.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSpots(
    @SerializedName("data")
    val `data`: List<SpotData>
)

@Serializable
data class SpotData(
    @SerializedName("des")
    val des: String,
    @SerializedName("loc")
    val loc: String,
    @SerializedName("mbti")
    val mbti: List<String>,
    @SerializedName("sid")
    val sid: String,
    @SerializedName("tag")
    val tag: List<String>,
    @SerializedName("title")
    val title: String
)