package com.sunul.sunul.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTest(
    @SerializedName("message")
    val message:String
)
