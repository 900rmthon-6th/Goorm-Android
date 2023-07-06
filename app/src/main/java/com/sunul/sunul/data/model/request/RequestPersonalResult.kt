package com.sunul.sunul.data.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPersonalResult(
    @SerializedName("ans")
    val ans: List<String>,
    @SerializedName("uid")
    val uid: String
)