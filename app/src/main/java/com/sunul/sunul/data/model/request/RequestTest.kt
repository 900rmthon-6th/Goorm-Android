package com.sunul.sunul.data.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RequestTest(
    @SerializedName("message")
    val message:String
)
