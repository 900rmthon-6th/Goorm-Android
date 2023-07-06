package com.sunul.sunul.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseQnA(
    @SerializedName("data")
    val `data`: List<Data>
)
@Serializable
data class Data(
    @SerializedName("ans")
    val ans: List<String>,
    @SerializedName("qid")
    val qid: String,
    @SerializedName("que")
    val que: String
)